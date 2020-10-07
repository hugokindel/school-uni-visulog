package up.visulog.analyzer.plugin;

import org.junit.Test;
import up.visulog.gitrawdata.Commit;
import up.visulog.gitrawdata.CommitBuilder;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;

/** This is a unit test class to test the CountCommitsPerAuthorPlugin plugin. */
public class TestCountCommitsPerAuthor {
    /** Check whether the number of authors is preserved and that the sum of the commits of
    * each author is equal to the total number of commits.
    */
    @Test
    public void checkCommitSum() {
        var log = new ArrayList<Commit>();
        String[] authors = {"foo", "bar", "baz"};
        var entries = 20;

        for (int i = 0; i < entries; i++) {
            log.add(new CommitBuilder(-1).setAuthor(authors[i % 3]).createCommit());
        }

        var res = CountCommitsPerAuthor.processLog(log);
        assertEquals(authors.length, res.getCommitsPerAuthor().size());

        var sum = res.getCommitsPerAuthor().values().stream().reduce(0, Integer::sum);
        assertEquals(entries, sum.longValue());
    }
}
