package maj;

import com.google.gson.annotations.Expose;

import java.util.Objects;

public class Player {
    @Expose(serialize = true, deserialize = true)
    private final long id;

    @Expose(serialize = true, deserialize = true)
    private final String displayName;

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
}
