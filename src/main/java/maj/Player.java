package maj;

import java.util.Objects;

public class Player {
    private long id;
    private String displayName;

    public Player() {
    }

    public Player(long id, String displayName) {
        this.id = id;
        this.displayName = displayName;
    }

    public long getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id == player.id &&
               displayName.equals(player.displayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, displayName);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                '}';
    }
}
