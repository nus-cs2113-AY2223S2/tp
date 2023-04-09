package seedu.mealcompanion.router;

/**
 * Represents a name, route pair that can be matched to a token in the entered command.
 */
public class RouteDescription {
    /**
     * The name of the route to be matched to the entered command
     */
    private String routeName;
    /**
     * The route to resolve the name to
     */
    private Routable routable;

    public RouteDescription(String routeName, Routable routable) {
        assert !routeName.isEmpty() && routeName != null;
        assert routable != null;
        this.routeName = routeName;
        this.routable = routable;
    }

    /**
     * Returns <code>routeName</code>.
     *
     * @return the name of the route to be matched to the entered command.
     */
    public String getRouteName() {
        return routeName;
    }

    /**
     * Returns <code>routable</code>.
     *
     * @return the route to resolve the name to.
     */
    public Routable getRoutable() {
        return routable;
    }
}
