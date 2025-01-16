import java.io.*;

class Song { // Class for a song with attributes artist name, song title, play count, and duration.
    private String artistName;
    private String songTitle;
    private int playCount;
    private int duration; // Duration in seconds

    public Song(String artistName, String songTitle, int playCount, int duration) {
        this.artistName = artistName;
        this.songTitle = songTitle;
        this.playCount = playCount;
        this.duration = duration;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public int getPlayCount() {
        return playCount;
    }

    public int getDuration() {
        return duration; // Get song duration
    }

    @Override
    public String toString() {
        return "Song Title: " + songTitle + ", Artist: " + artistName + ", Play Count: " + playCount + ", Duration: " + duration + " seconds";
    }

    public String toFileFormat() { // Method to get song details in a format for saving to file
        return artistName + "," + songTitle + "," + playCount + "," + duration;
    }

    public static Song fromFileFormat(String line) { // Method to create a Song object from a line in the file
        String[] parts = line.split(",");

        if (parts.length != 4) {
            System.out.println("Skipping malformed line: " + line);
            return null;
        }

        try {
            return new Song(parts[0], parts[1], Integer.parseInt(parts[2]), Integer.parseInt(parts[3])); // Parse duration
        } catch (NumberFormatException e) {
            System.out.println("Invalid play count or duration in line: " + line);
            return null;
        }
    }
}
