import android.content.ContentValues.TAG
import android.os.AsyncTask
import android.util.Log
import com.example.projetcubemobile.Singleton
import com.example.projetcubemobile.models.CategorieModel
import com.example.projetcubemobile.models.SubjectForumModel
import com.example.projetcubemobile.models.UtilisateurModel
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.URL
import java.util.LinkedList

import com.google.gson.Gson
import com.google.gson.JsonParser
import okhttp3.*
import java.io.IOException
import java.util.*

class SubjectForumApi {

    fun getAllCategories(onComplete: (LinkedList<CategorieModel>) -> Unit) {
        val url = URL("http://10.0.2.2:5136/api/Categories")

        AsyncTask.execute {
            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "GET"

                println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")

                val response = inputStream.bufferedReader().use { it.readText() }
                val categories = parseCategoriesJson(response)

                onComplete(categories)
            }
        }
    }



    private fun parseCategoriesJson(jsonString: String): LinkedList<CategorieModel> {
        val categories = LinkedList<CategorieModel>()

        val jsonArray = JSONArray(jsonString)
        for (i in 0 until jsonArray.length()) {
            val jsonCategory = jsonArray.getJSONObject(i)

            val category = CategorieModel()
            category.id = jsonCategory.getInt("id")
            category.nom = jsonCategory.getString("nom")

            val jsonSubjectForums = jsonCategory.optJSONArray("subjectsForums")
            if (jsonSubjectForums != null) {
                for (j in 0 until jsonSubjectForums.length()) {
                    val jsonSubjectForum = jsonSubjectForums.getJSONObject(j)

                    val subjectForum = SubjectForumModel()
                    subjectForum.id = jsonSubjectForum.getInt("id")
                    subjectForum.idCategorie = jsonSubjectForum.getInt("idCategorie")
                    subjectForum.title = jsonSubjectForum.getString("title")
                    subjectForum.text = jsonSubjectForum.getString("text")

                    category.subjectForumModels.add(subjectForum)
                }
            }
            categories.add(category)
        }

        return categories
    }
    fun getSubjectForumByUserCurrent(callback: (LinkedList<SubjectForumModel>?) -> Unit) {
        val client = OkHttpClient()
        val url = "http://10.0.2.2:5136/api/SubjectForums/ByUserId/${Singleton.CurrentUser!!.id}"
        val request = Request.Builder()
            .url(url)
            .get()
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, "Failed to get subject forums by user current", e)
                callback(null)
            }

            override fun onResponse(call: Call, response: Response) {
                try {
                    val json = response.body?.string()
                    val gson = Gson()
                    val jsonArray = JsonParser.parseString(json).asJsonArray

                    val subjectForums = LinkedList<SubjectForumModel>()

                    for (i in 0 until jsonArray.size()) {
                        val jsonObject = jsonArray.get(i).asJsonObject

                        val id = jsonObject.get("id").asInt
                        val idCategorie = jsonObject.get("idCategorie").asInt
                        val idUtilisateur = jsonObject.get("idUtilisateur").asInt
                        val title = jsonObject.get("title").asString
                        val text = jsonObject.get("text").asString

                        val subjectForumModel = SubjectForumModel()
                        subjectForumModel.id = id
                        subjectForumModel.idCategorie = idCategorie
                        subjectForumModel.idUtilisateur = idUtilisateur
                        subjectForumModel.title = title
                        subjectForumModel.text = text

                        subjectForums.add(subjectForumModel)
                    }
                    callback(subjectForums)
                } catch (e: Exception) {
                    Log.e(TAG, "Failed to parse subject forums by user current response", e)
                    callback(null)
                }
            }
        })
    }




    fun createSubjectForum(subjectForum: SubjectForumModel) {
        val url = URL("http://10.0.2.2:5136/api/SubjectForums")

        AsyncTask.execute {
            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "POST"
                doOutput = true

                // Ajouter l'en-tête Content-Type
                setRequestProperty("Content-Type", "application/json")

                val jsonObject = JSONObject()
                jsonObject.put("idCategorie", subjectForum.idCategorie)
                jsonObject.put("idUtilisateur", subjectForum.idUtilisateur)
                jsonObject.put("title", subjectForum.title)
                jsonObject.put("text", subjectForum.text)

                val outputStream = outputStream
                val writer = BufferedWriter(OutputStreamWriter(outputStream, "UTF-8"))
                writer.write(jsonObject.toString())
                writer.flush()
                writer.close()
                outputStream.close()

                println("\nSent 'POST' request to URL : $url; Response Code : $responseCode")
                val response = inputStream.bufferedReader().use { it.readText() }
                println("Response JSON : $response")
            }
        }
    }

}

