/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package p3final;
import org.neo4j.driver.*;
import org.neo4j.driver.Record;
import org.neo4j.driver.Values;
/**
 *
 * @author Samuel GR
 */
public class P3Final {

    /**
     * @param args the command line arguments
     */


public class VideoGameCRUD {

    private final Driver driver;

    public VideoGameCRUD(String uri, String username, String password) {
        this.driver = GraphDatabase.driver(uri, AuthTokens.basic(username, password));
    }

    public void close() {
        driver.close();
    }

    public void createVideoGame(String name, String genre) {
        try (Session session = driver.session()) {
            session.run("CREATE (:VideoGame {name: $name, genre: $genre})",
                    Values.parameters("name", name, "genre", genre));
        }
    }

    public void updateVideoGame(String name, String newName) {
        try (Session session = driver.session()) {
            session.run("MATCH (vg:VideoGame {name: $name}) SET vg.name = $newName",
                    Values.parameters("name", name, "newName", newName));
        }
    }

    public void deleteVideoGame(String name) {
        try (Session session = driver.session()) {
            session.run("MATCH (vg:VideoGame {name: $name}) DELETE vg",
                    Values.parameters("name", name));
        }
    }

    public void getAllVideoGames() {
        try (Session session = driver.session()) {
            Result result = session.run("MATCH (vg:VideoGame) RETURN vg.name AS name, vg.genre AS genre");
            while (result.hasNext()) {
                Record record = result.next();
                String name = record.get("name").asString();
                String genre = record.get("genre").asString();
                System.out.println("Name: " + name + ", Genre: " + genre);
            }
        }
    }

    public static void main(String[] args) {
        // Configura la conexión a la base de datos de Neo4j
        String uri = "bolt://localhost:7474";
        String username = "neo4j";
        String password = "55431393";

        // Crea una instancia de VideoGameCRUD y realiza operaciones CRUD
        VideoGameCRUD crud = new VideoGameCRUD(uri, username, password);
        crud.createVideoGame("Tetris", "Puzzle");
        crud.createVideoGame("Final Fantasy VII", "RPG");
        crud.createVideoGame("Super Mario Bros", "Platformer");
        crud.updateVideoGame("Super Mario Bros", "Super Mario Odyssey");
        crud.deleteVideoGame("Final Fantasy VII");
        crud.getAllVideoGames();

        // Cierra la conexión
        crud.close();
    }
}
}
