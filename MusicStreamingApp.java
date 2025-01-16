import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class MusicStreamingApp { // Main application class
    private ArrayList<Song> songList;
    private final String filePath = "Song Storage.txt";

    public MusicStreamingApp() {
        songList = new ArrayList<>();
        loadSongsFromFile();
    }

    public void addSong(String artistName, String songTitle, int playCount, int duration) { // Method to add a new song and save to file
        Song newSong = new Song(artistName, songTitle, playCount, duration);
        songList.add(newSong);
        System.out.println("Song added: " + newSong);
        saveSongsToFile();
    }

    public void removeSong(String songTitle) { // Method to remove a song by title and save to file
        boolean found = false;
        for (Song song : songList) {
            if (song.getSongTitle().equalsIgnoreCase(songTitle)) {
                songList.remove(song);
                System.out.println("Song removed: " + song);
                found = true;
                saveSongsToFile();
                break;
            }
        }
        if (!found) {
            System.out.println("Song not found in the list.");
        }
    }

    public void printAllSongs() { // Method to print all songs
        System.out.println("\u001B[33mList of all songs:\u001B[0m"); // Yellow title
        for (Song song : songList) {
            System.out.println("\u001B[36m" + song.getSongTitle() + "\u001B[0m" + " \u001B[37mby " + song.getArtistName() + "\u001B[0m" + " \u001B[32mPlay Count: " + song.getPlayCount() + "\u001B[0m");
        }
    }

    public void printSongsOverPlayCount(int playCountThreshold) { // Method to print songs over a certain play count
        System.out.println("\u001B[33mSongs with play count over " + playCountThreshold + ":\u001B[0m"); // Yellow title
        for (Song song : songList) {
            if (song.getPlayCount() > playCountThreshold) {
                System.out.println("\u001B[36m" + song.getSongTitle() + "\u001B[0m" + " \u001B[37mby " + song.getArtistName() + "\u001B[0m" + " \u001B[32mPlay Count: " + song.getPlayCount() + "\u001B[0m");
            }
        }
    }

    public void playSongs() { // Method to play songs with the option to skip or stop
        if (songList.isEmpty()) {
            System.out.println("No songs available to play.");
            return;
        }

        Random random = new Random();
        boolean continuePlaying = true;

        while (continuePlaying) {
            int songIndex = random.nextInt(songList.size());
            Song songToPlay = songList.get(songIndex);

            System.out.println("\n\u001B[35mNow Playing: \u001B[0m");
            System.out.println("\u001B[36m" + songToPlay + "\u001B[0m"); // Cyan for song details
            System.out.println("Enjoy the song!");

            // Wait for song duration with option to skip or stop
            long startTime = System.currentTimeMillis();
            long endTime = startTime + 2000; // Play for 2 seconds (simulating)

            Scanner scanner = new Scanner(System.in);
            boolean songFinished = false;

            while (System.currentTimeMillis() < endTime && !songFinished) {
                System.out.print("\u001B[37mPress 's' to skip the song, or 'q' to stop playing... \u001B[0m");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("s")) {
                    System.out.println("\u001B[32mSong skipped!\u001B[0m");
                    songFinished = true;
                } else if (input.equalsIgnoreCase("q")) {
                    System.out.println("\u001B[31mStopping the music...\u001B[0m");
                    continuePlaying = false; // Stop playing
                    songFinished = true;
                }
            }

            if (!songFinished) {
                try {
                    // Simulate song playing by pausing the thread for the song duration
                    Thread.sleep(2000); // Simulate 2 seconds of song playing
                    System.out.println("\nSong finished.");
                } catch (InterruptedException e) {
                    System.out.println("Error during song playback.");
                }
            }

            // After song duration or skip, pick the next song randomly
            if (continuePlaying) {
                System.out.println("Next song will play.");
            }
        }
    }

    private void saveSongsToFile() { // Method to save all songs to the file
        try (PrintWriter writer = new PrintWriter(new FileWriter(filePath))) {
            for (Song song : songList) {
                writer.println(song.toFileFormat());
            }
        } catch (IOException e) {
            System.out.println("Error saving songs to file: " + e.getMessage());
        }
    }

    private void loadSongsFromFile() { // Method to load songs from the file
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Song song = Song.fromFileFormat(line);
                if (song != null) {
                    songList.add(song);
                }
            }
        } catch (IOException e) {
            System.out.println("No existing song storage found. Starting with an empty list.");
        }
    }

    public static void main(String[] args) {
        MusicStreamingApp app = new MusicStreamingApp();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) { // Basic Menu
            System.out.println("\n\u001B[33m--- Music Streaming App ---\u001B[0m"); // Yellow title
            System.out.println("\u001B[37m1. Add a new song\u001B[0m"); // White options
            System.out.println("\u001B[37m2. Remove a song\u001B[0m");
            System.out.println("\u001B[37m3. Print all songs\u001B[0m");
            System.out.println("\u001B[37m4. Print songs over a play count\u001B[0m");
            System.out.println("\u001B[37m5. Play songs\u001B[0m");
            System.out.println("\u001B[37m6. Exit\u001B[0m");
            System.out.print("\u001B[35mChoose an option: \u001B[0m"); // Purple for "Choose an option"

            int choice = getValidInt(scanner); // Handle invalid input for menu selection
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1: // For adding songs
                    System.out.print("Enter artist name: ");
                    String artist = scanner.nextLine();
                    System.out.print("Enter song title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter play count: ");
                    int playCount = getValidInt(scanner); // Handle invalid input for play count
                    System.out.print("Enter song duration (in seconds): ");
                    int duration = getValidInt(scanner); // Handle invalid input for duration
                    app.addSong(artist, title, playCount, duration); // Add the song with duration
                    break;
                case 2: // For removing songs
                    System.out.print("Enter song title to remove: ");
                    String titleToRemove = scanner.nextLine();
                    app.removeSong(titleToRemove);
                    break;
                case 3:
                    app.printAllSongs();
                    break;
                case 4: // Search for songs above play count
                    System.out.print("Enter play count threshold: ");
                    int threshold = getValidInt(scanner); // Handle invalid input for threshold
                    app.printSongsOverPlayCount(threshold);
                    break;
                case 5: // For playing songs
                    app.playSongs();
                    break;
                case 6: // For exiting the application
                    running = false;
                    System.out.println("Exiting the application.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        scanner.close();
    }

    // Helper method to get a valid integer input
    private static int getValidInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // Consume the invalid input
        }
        return scanner.nextInt();
    }
}
