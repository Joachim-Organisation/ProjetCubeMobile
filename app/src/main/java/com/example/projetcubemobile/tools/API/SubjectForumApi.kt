import android.os.AsyncTask
import com.example.projetcubemobile.models.CategorieModel
import com.example.projetcubemobile.models.SubjectForumModel
import org.json.JSONArray
import java.net.HttpURLConnection
import java.net.URL
import java.util.LinkedList

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

            val jsonSubjectForums = jsonCategory.getJSONArray("subjectforums")
            for (j in 0 until jsonSubjectForums.length()) {
                val jsonSubjectForum = jsonSubjectForums.getJSONObject(j)

                val subjectForum = SubjectForumModel()
                subjectForum.id = jsonSubjectForum.getInt("id")
                subjectForum.idCategorie = jsonSubjectForum.getInt("idCategorie")
                subjectForum.title = jsonSubjectForum.getString("title")
                subjectForum.text = jsonSubjectForum.getString("text")

                category.subjectForumModels.add(subjectForum)
            }

            categories.add(category)
        }

        return categories
    }
}

