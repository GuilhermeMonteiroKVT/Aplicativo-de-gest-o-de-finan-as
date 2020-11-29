package ferramentas;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

public class EventosDB extends SQLiteOpenHelper {

    private Context contexto;
    public EventosDB(Context cont){
        super(cont, "evento", null, 1);
        contexto = cont;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        final String criaTabela = "CREATE TABLE IF NOT EXISTS evento(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT," +
                "valor REAL, imagem TEXT, dataocorreu DATE, datacadastro DATE, datavalida DATE)";

        db.execSQL(criaTabela);
    }

    public void insereEvento(){

        try(SQLiteDatabase db = this.getWritableDatabase();) {

            String sql = "INSERT into evento(nome, VALUES ('evento1', 89)";

            db.execSQL(sql);


        }catch (SQLException ex){
            ex.printStackTrace();
        }


    }

    public void atualizaEvento(){

    }

    public void buscaEvento(){

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Ficara parado ate a atualização da Activity de update (funcionalidade)

    }
}
