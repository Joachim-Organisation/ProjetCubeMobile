import android.os.AsyncTask
import com.example.projetcubemobile.models.UtilisateurModel
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

class UtilisateurApi {

    fun getUtilisateurByEmailAndPassword(email: String, password: String, callback: (UtilisateurModel?) -> Unit) {
        val url = URL("http://10.0.2.2:5136/api/Utilisateurs/authenticate?mail=$email&password=$password")

        AsyncTask.execute {
            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "GET"

                println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    val response = inputStream.bufferedReader().use { it.readText() }
                    val utilisateur = parseUtilisateurJson(response)
                    callback(utilisateur)
                } else {
                    callback(null)
                }
            }
        }
    }

    private fun parseUtilisateurJson(jsonString: String): UtilisateurModel? {
        try {
            val jsonObject = JSONObject(jsonString)

            val utilisateur = UtilisateurModel()
            utilisateur.id = jsonObject.optInt("id", -1)
            utilisateur.idRole = jsonObject.optInt("idRole", -1)
            utilisateur.prenom = jsonObject.optString("prenom", null)
            utilisateur.nom = jsonObject.optString("name", null)
            utilisateur.mail = jsonObject.optString("mail", null)
            utilisateur.isActiver = jsonObject.optBoolean("isActiver", false)
            utilisateur.password = jsonObject.optString("password", null)

            return utilisateur
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return null
    }

}
