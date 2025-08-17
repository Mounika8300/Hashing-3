import java.util.*;

public class Main {

    public static Map<String, List<String>> favoritegenre(Map<String, List<String>> userMap, Map<String, List<String>> genreMap) {
        Map<String, String> songToGenre = new HashMap<>();

        // Step 1: Build reverse map: song -> genre
        for (Map.Entry<String, List<String>> entry : genreMap.entrySet()) {
            String genre = entry.getKey();
            for (String song : entry.getValue()) {
                songToGenre.put(song, genre);
            }
        }

        Map<String, List<String>> result = new HashMap<>();

        // Step 2: For each user, count genre frequencies
        for (Map.Entry<String, List<String>> entry : userMap.entrySet()) {
            String user = entry.getKey();
            List<String> songs = entry.getValue();

            Map<String, Integer> genreCount = new HashMap<>();
            int maxCount = 0;

            for (String song : songs) {
                String genre = songToGenre.get(song);
                if (genre != null) {
                    genreCount.put(genre, genreCount.getOrDefault(genre, 0) + 1);
                    maxCount = Math.max(maxCount, genreCount.get(genre));
                }
            }

            // Step 3: Collect genres with max count
            List<String> favGenres = new ArrayList<>();
            for (Map.Entry<String, Integer> g : genreCount.entrySet()) {
                if (g.getValue() == maxCount) {
                    favGenres.add(g.getKey());
                }
            }

            result.put(user, favGenres);
        }

        return result;
    }

    public static void main(String[] args) {
        HashMap<String, List<String>> userSongs = new HashMap<>();
        userSongs.put("David", Arrays.asList("song1", "song2", "song3", "song4", "song8"));
        userSongs.put("Emma", Arrays.asList("song5", "song6", "song7"));

        HashMap<String, List<String>> songGenres = new HashMap<>();
        songGenres.put("Rock", Arrays.asList("song1", "song3"));
        songGenres.put("Dubstep", Arrays.asList("song7"));
        songGenres.put("Techno", Arrays.asList("song2", "song4"));
        songGenres.put("Pop", Arrays.asList("song5", "song6"));
        songGenres.put("Jazz", Arrays.asList("song8", "song9"));

        Map<String, List<String>> res = favoritegenre(userSongs, songGenres);

        System.out.println(res);
    }
}
