import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class ConnectionDataBase
 {

     private val host = "localhost"
     private val port = 3306;
     private val database = "freedb_projet cube";
     private val username = "root";
     private val password = ""

     private lateinit var connection: Connection;

    fun getConnection() : Deferred<Int> = GlobalScope.async{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver")
            Log.e("HELA","la fonction fonctionne !")
            connection = DriverManager.getConnection(
                "jdbc:mysql://$host:$port",
                username, password
            )
            Log.e("HELA","la fonction fonctionne !")
        } catch (e: SQLException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return@async 42
    }

     fun testRequete() : Deferred<Int> = GlobalScope.async{

         if (connection != null) {
             val statement = connection.createStatement()
             val query = "SELECT * FROM subjectForum"
             val resultSet = statement.executeQuery(query)

             while (resultSet.next()) {
                 val id = resultSet.getInt("id")
                 val username = resultSet.getString("title")
                 val email = resultSet.getString("description")
                 Log.e("Requête","id: $id, username: $username, email: $email")
                 Log.e("HELA","la fonction fonctionne !", )
             }

             resultSet.close()
             statement.close()
         } else {
             println("La connexion à la base de données a échoué")
         }
         return@async 42
     }
}
