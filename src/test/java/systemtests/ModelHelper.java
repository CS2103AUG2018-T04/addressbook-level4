package systemtests;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.saveit.model.Issue;
import seedu.saveit.model.Model;

/**
 * Contains helper methods to set up {@code Model} for testing.
 */
public class ModelHelper {
    private static final Predicate<Issue> PREDICATE_MATCHING_NO_ISSUES = unused -> false;

    /**
     * Updates {@code model}'s filtered list to display only {@code toDisplay}.
     */
    public static void setFilteredList(Model model, List<Issue> toDisplay) {
        Optional<Predicate<Issue>> predicate =
                toDisplay.stream().map(ModelHelper::getPredicateMatching).reduce(Predicate::or);
        model.updateFilteredIssueList(predicate.orElse(PREDICATE_MATCHING_NO_ISSUES));
    }

    /**
     * @see ModelHelper#setFilteredList(Model, List)
     */
    public static void setFilteredList(Model model, Issue... toDisplay) {
        setFilteredList(model, Arrays.asList(toDisplay));
    }

    /**
     * Returns a predicate that evaluates to true if this {@code Issue} equals to {@code other}.
     */
    private static Predicate<Issue> getPredicateMatching(Issue other) {
        return issue -> issue.equals(other);
    }

    public static void setSortedList(Model model, List<Issue> toDisplay) {
        Comparator<Issue> comparator = getComparatorMatching(toDisplay);
        model.updateFilteredAndSortedIssueList(comparator);
    }

    public static void setSortedList(Model model, Issue... toDisplay) {
        setSortedList(model, Arrays.asList(toDisplay));
    }

    private static Comparator<Issue> getComparatorMatching(List<Issue> issues) {
        return new Comparator<Issue>() {
            @Override
            public int compare(Issue o1, Issue o2) {
                return issues.indexOf(o1) - issues.indexOf(o2);
            }
        };
    }
}
