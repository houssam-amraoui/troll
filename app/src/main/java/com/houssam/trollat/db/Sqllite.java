package com.houssam.trollat.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

import com.houssam.trollat.model.Comment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Sqllite extends SQLiteOpenHelper {
    /*
    add comment
    add replay
    add like
    get comment
    get replay
    get like
     */




    private static Sqllite con = null;
    public static final String dbname= "gcom.db"  ;


    public Sqllite(Context context) {

        super(context, dbname, null, 1);
    }
    public static Sqllite Getnewinstans(Context context){
        if(con == null)
            con = new Sqllite(context);
        return con;
    }
/*
insert into userapp (nom,prenom,datene) values('houssam','amraoui','15/04/1999');
insert into comment(body,pdate,nblike,iduser) values ('hello comment','17/08/2019',1,1);
insert into replay values (1,'hello replay','17/08/2019',1,1)
insert into imagel values (1,'image.com',1)
*/


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE if not Exists `userapp` (`iduser` INTEGER PRIMARY KEY AUTOINCREMENT, `nom` varchar ( 100 ), `prenom` varchar ( 100 ), `datene` date );");
        db.execSQL("CREATE TABLE if not Exists `comment` (`idcomment`INTEGER PRIMARY KEY AUTOINCREMENT,`body`varchar ( 100 ),`pdate`date,`nblike`int,`iduser`int,FOREIGN KEY(`iduser`) REFERENCES `userapp`(`iduser`));");
        db.execSQL("CREATE TABLE if not Exists `replay`  (`idrelay` INTEGER PRIMARY KEY AUTOINCREMENT, `body` varchar ( 100 ), `pdate` date, `nblike` int, `idcomment` int, FOREIGN KEY(`idcomment`) REFERENCES `comment`(`idcomment`) );");
        db.execSQL("CREATE TABLE if not Exists `imagel`  (`idimage`INTEGER PRIMARY KEY AUTOINCREMENT, `imagelink`varchar ( 100 ), `idcomment`int, FOREIGN KEY(`idcomment`) REFERENCES `comment`(`idcomment`) );");
         }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
    public void DeletAll(){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("DELETE FROM imagel");
        database.execSQL("delete from replay");
        database.execSQL("DELETE FROM comment");
        database.execSQL("delete from userapp");
    }
    public void addAll(){
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("insert into userapp (nom,prenom,datene) values('houssam','amraoui','15/04/1999');\n" +
                "INSERT into comment(body,pdate,nblike,iduser) values ('hello comment','17/08/2019',1,1);\n" +
                "insert into replay values (1,'hello replay','17/08/2019',1,1);\n" +
                "insert into imagel values (1,'image.com',1);");

    }
    public void addComment(Comment comment){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues=new ContentValues();
            contentValues.put("body",comment.getBody());

            SimpleDateFormat sdf =new  SimpleDateFormat("dd MM yyyy", Locale.ENGLISH);
            try {
                contentValues.put("pdate", sdf.format(comment.getDate()));
            }catch (Exception e){}
            contentValues.put("nblike",comment.getNblike());
            contentValues.put("iduser", 1);

            database.insert("comment",null,contentValues);
            contentValues.clear();
    }

    public  void addAllComment(ArrayList<Comment> comments){

        for (Comment comment:comments) {
            addComment(comment);
        }
    }

    public

    public ArrayList<Comment> getAllComment(){
        ArrayList<Comment> arr=new  ArrayList<Comment>();

        SQLiteDatabase database = getReadableDatabase();
        Cursor re = database.rawQuery("select * from allposts",null);
        re.moveToFirst();
        while (!re.isAfterLast()){
            Post post=new Post();
            post.title=  re.getString(re.getColumnIndex("title"));
            SimpleDateFormat sdf =new  SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

            try {
                post.pubDate = sdf.parse(re.getString(re.getColumnIndex("pubDate")));
            }catch (Exception h){}
            post.link=  re.getString(re.getColumnIndex("link"));

            post.guid=   re.getString(re.getColumnIndex("guid"));
            post.author=   re.getString(re.getColumnIndex("author"));

            arr.add(post);
            re.moveToNext();
        }
        return arr;
    }


    public int getCount(){
        // use qury "select count * tablename
        SQLiteDatabase database = getReadableDatabase();
        Cursor re = database.rawQuery("select * from allposts",null);
        return re.getCount();
    }

    public ArrayList<String> getalldb(String bd){
        ArrayList<String> fav=new ArrayList<String>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor re = database.rawQuery("select * from "+bd+" ",null);
        re.moveToFirst();
        while (!re.isAfterLast()){
            fav.add(re.getString(re.getColumnIndex("id")));
            re.moveToNext(); }
        return fav; }
    // select * from `aa` where `id` IN ('1', '3', '5');

    public void adddbpost(String id,String bd){

        SQLiteDatabase database = getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("id",id);
        database.insert(bd,null,contentValues);

        contentValues.clear();}

    public void deletdbpost(String id,String bdtabel){
        SQLiteDatabase database = getWritableDatabase();
        database.delete(bdtabel,"id = ?", new String[]{id});
        //database.execSQL("DELETE FROM '"+bdtabel+"';");
        // +"' where 'id' like '"+id
    }

}

