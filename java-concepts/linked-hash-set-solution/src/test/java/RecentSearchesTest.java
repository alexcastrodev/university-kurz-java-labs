import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RecentSearches")
class RecentSearchesTest {

    @Test
    @DisplayName("should keep the searches in insertion order")
    void shouldKeepSearchesInInsertionOrder() {
        RecentSearches searches = new RecentSearches(10);
        searches.record("java");
        searches.record("spring");
        searches.record("docker");

        assertEquals(List.of("java", "spring", "docker"), searches.history());
    }

    @Test
    @DisplayName("should not move an already recorded query to the end")
    void shouldNotMoveAlreadyRecordedQueryToTheEnd() {
        RecentSearches searches = new RecentSearches(10);
        searches.record("java");
        searches.record("spring");
        searches.record("java");

        assertEquals(List.of("java", "spring"), searches.history());
    }

    @Test
    @DisplayName("should report whether a query has been searched")
    void shouldReportWhetherQueryHasBeenSearched() {
        RecentSearches searches = new RecentSearches(10);
        searches.record("java");

        assertTrue(searches.hasSearched("java"));
        assertFalse(searches.hasSearched("kotlin"));
    }

    @Test
    @DisplayName("should evict the oldest query when capacity is exceeded")
    void shouldEvictTheOldestQueryWhenCapacityIsExceeded() {
        RecentSearches searches = new RecentSearches(2);
        searches.record("a");
        searches.record("b");
        searches.record("c");

        assertEquals(List.of("b", "c"), searches.history());
        assertFalse(searches.hasSearched("a"));
    }

    @Test
    @DisplayName("should not evict anything when recording an already present query")
    void shouldNotEvictAnythingWhenRecordingAnAlreadyPresentQuery() {
        RecentSearches searches = new RecentSearches(2);
        searches.record("a");
        searches.record("b");
        searches.record("a");

        assertEquals(List.of("a", "b"), searches.history());
    }

    @Test
    @DisplayName("should return the last recorded query as the most recent one")
    void shouldReturnTheLastRecordedQueryAsTheMostRecentOne() {
        RecentSearches searches = new RecentSearches(10);
        searches.record("a");
        searches.record("b");
        searches.record("c");

        assertEquals("c", searches.mostRecent());
    }

    @Test
    @DisplayName("should return null as the most recent query when nothing was recorded")
    void shouldReturnNullAsTheMostRecentQueryWhenNothingWasRecorded() {
        RecentSearches searches = new RecentSearches(10);

        assertNull(searches.mostRecent());
    }
}
