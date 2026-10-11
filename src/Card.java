import java.util.LinkedHashMap;
import java.util.Map;

/**
 * One driver card. Ratings are game-balance scores from 1 to 100,
 * not official FIA statistics or a definitive ranking of drivers.
 */
public class Card {
    private final String name;
    private final String teamOrEra;
    private final Map<String, Integer> ratings;

    public Card(String name, String teamOrEra, int racecraft, int qualifying,
                int consistency, int overtaking, int wetWeather,
                int tireManagement, int adaptability, int peakPerformance) {
        this.name = name;
        this.teamOrEra = teamOrEra;
        ratings = new LinkedHashMap<>();
        ratings.put("Racecraft", racecraft);
        ratings.put("Qualifying", qualifying);
        ratings.put("Consistency", consistency);
        ratings.put("Overtaking", overtaking);
        ratings.put("Wet Weather", wetWeather);
        ratings.put("Tire Management", tireManagement);
        ratings.put("Adaptability", adaptability);
        ratings.put("Peak Performance", peakPerformance);

        for (int value : ratings.values()) {
            if (value < 1 || value > 100) {
                throw new IllegalArgumentException("Ratings must be from 1 to 100.");
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getTeamOrEra() {
        return teamOrEra;
    }

    public int getRating(String category) {
        Integer value = ratings.get(category);
        if (value == null) {
            throw new IllegalArgumentException("Unknown category: " + category);
        }
        return value;
    }

    public Map<String, Integer> getRatings() {
        return new LinkedHashMap<>(ratings);
    }

    public static String[] getCategories() {
        return new String[] {
            "Racecraft", "Qualifying", "Consistency", "Overtaking",
            "Wet Weather", "Tire Management", "Adaptability", "Peak Performance"
        };
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(name).append(" (").append(teamOrEra).append(")\n");
        for (Map.Entry<String, Integer> entry : ratings.entrySet()) {
            result.append(String.format("  %-18s %3d/100%n",
                    entry.getKey() + ":", entry.getValue()));
        }
        return result.toString();
    }
}
