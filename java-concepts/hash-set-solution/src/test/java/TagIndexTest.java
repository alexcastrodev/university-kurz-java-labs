import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TagIndex")
class TagIndexTest {

    private static TagIndex indexOf(String... tags) {
        TagIndex index = new TagIndex();
        for (String tag : tags) {
            index.addTag(tag);
        }
        return index;
    }

    @Test
    @DisplayName("should report true when adding a brand new tag")
    void shouldReportTrueWhenAddingANewTag() {
        TagIndex index = new TagIndex();

        assertTrue(index.addTag("java"));
        assertEquals(1, index.size());
    }

    @Test
    @DisplayName("should report false when adding a duplicate tag")
    void shouldReportFalseWhenAddingADuplicateTag() {
        TagIndex index = new TagIndex();
        index.addTag("java");

        assertFalse(index.addTag("java"));
        assertEquals(1, index.size());
    }

    @Test
    @DisplayName("should treat tags that differ only in case as the same tag")
    void shouldTreatTagsThatDifferOnlyInCaseAsTheSameTag() {
        TagIndex index = new TagIndex();
        index.addTag("Java");
        index.addTag("java");

        assertEquals(1, index.size());
    }

    @Test
    @DisplayName("should look up tags case-insensitively")
    void shouldLookUpTagsCaseInsensitively() {
        TagIndex index = new TagIndex();
        index.addTag("Java");

        assertTrue(index.hasTag("java"));
    }

    @Test
    @DisplayName("should return only the tags present in both indexes")
    void shouldReturnOnlyTheTagsPresentInBothIndexes() {
        TagIndex first = indexOf("java", "spring", "testing");
        TagIndex second = indexOf("spring", "testing", "docker");

        assertEquals(Set.of("spring", "testing"), first.commonTagsWith(second));
    }

    @Test
    @DisplayName("should not mutate either index when computing common tags")
    void shouldNotMutateEitherIndexWhenComputingCommonTags() {
        TagIndex first = indexOf("java", "spring", "testing");
        TagIndex second = indexOf("spring", "testing", "docker");

        first.commonTagsWith(second);

        assertEquals(3, first.size());
        assertEquals(3, second.size());
    }

    @Test
    @DisplayName("should return the tags present in this index but not in the other")
    void shouldReturnTheTagsPresentInThisIndexButNotInTheOther() {
        TagIndex first = indexOf("java", "spring", "testing");
        TagIndex second = indexOf("spring", "testing", "docker");

        assertEquals(Set.of("java"), first.tagsOnlyInThis(second));
        assertEquals(Set.of("docker"), second.tagsOnlyInThis(first));
    }

    @Test
    @DisplayName("should not mutate either index when computing the difference")
    void shouldNotMutateEitherIndexWhenComputingTheDifference() {
        TagIndex first = indexOf("java", "spring", "testing");
        TagIndex second = indexOf("spring", "testing", "docker");

        first.tagsOnlyInThis(second);

        assertEquals(3, first.size());
        assertEquals(3, second.size());
    }

    @Test
    @DisplayName("should return every tag present in either index")
    void shouldReturnEveryTagPresentInEitherIndex() {
        TagIndex first = indexOf("java", "spring", "testing");
        TagIndex second = indexOf("spring", "testing", "docker");

        assertEquals(Set.of("java", "spring", "testing", "docker"), first.allTagsCombined(second));
    }
}
