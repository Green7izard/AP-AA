package nl.han.bas.permutation.inline;

import org.junit.Test;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Bas on 22-12-2015.
 */
public class AnagramTest
{
    @Test
    public void sameLetterTestWrongSize()
    {
        assertFalse(AnagramFinder.sameLetters("baan", "ban"));
        assertFalse(AnagramFinder.sameLetters("sap", "saap"));
    }

    @Test
    public void sameLetterTestSameWords()
    {
        assertTrue("de", AnagramFinder.sameLetters("de", "de"));
        assertTrue("het", AnagramFinder.sameLetters("het", "het"));
        assertTrue("friet", AnagramFinder.sameLetters("friet", "friet"));
        assertTrue("koffieboon", AnagramFinder.sameLetters("koffieboon", "koffieboon"));
        assertTrue("hefboomconstructie", AnagramFinder.sameLetters("hefboomconstructie", "hefboomconstructie"));
        assertTrue("minimumtemperaturen", AnagramFinder.sameLetters("minimumtemperaturen", "minimumtemperaturen"));
    }

    @Test
    public void sameLetterTestDifferentWords()
    {
        assertTrue("de", AnagramFinder.sameLetters("ed", "de"));
        assertTrue("het", AnagramFinder.sameLetters("het", "teh"));
        assertTrue("friet", AnagramFinder.sameLetters("friet", "fetri"));
        assertTrue("koffieboon", AnagramFinder.sameLetters("koffieboon", "okboffonie"));
        assertTrue("hefboomconstructie", AnagramFinder.sameLetters("hefboomconstructie", "hefbooconstructiem"));
        assertTrue("minimumtemperaturen", AnagramFinder.sameLetters("minimumtemperaturen", "eminimumtmperaturen"));
    }

    @Test
    public void anagramsEen()
    {
        AnagramFinder finder = new AnagramFinder();
        Map<String, Set<String>> result = finder.getAnagrams("een");
        assertEquals(1, result.size());
        assertNotNull(result.get("een"));
        assertEquals(1, result.get("een").size());
        assertEquals("een", result.get("een").iterator().next());
    }

    @Test
    public void anagramsEenEneNee()
    {
        AnagramFinder finder = new AnagramFinder();
        Map<String, Set<String>> result = finder.getAnagrams("een ene nee");
        assertEquals(3, result.size());


        assertNotNull(result.get("een"));
        assertEquals(3, result.get("een").size());
        Iterator<String> s = result.get("een").iterator();
        assertEquals("een", s.next());
        assertEquals("ene", s.next());
        assertEquals("nee", s.next());
        assertFalse(s.hasNext());

        assertNotNull(result.get("ene"));
        assertEquals(3, result.get("ene").size());
        s = result.get("ene").iterator();
        assertEquals("een", s.next());
        assertEquals("ene", s.next());
        assertEquals("nee", s.next());
        assertFalse(s.hasNext());

        assertNotNull(result.get("nee"));
        assertEquals(3, result.get("nee").size());
        s = result.get("nee").iterator();
        assertEquals("een", s.next());
        assertEquals("ene", s.next());
        assertEquals("nee", s.next());
        assertFalse(s.hasNext());
    }

    @Test
    public void anagramsEecEneNeeUnique()
    {
        AnagramFinder finder = new AnagramFinder();
        Map<String, Set<String>> result = finder.getUniqueAnagrams("een ene nee");
        assertEquals(1, result.size());


        assertNotNull(result.get("een"));
        assertEquals(3, result.get("een").size());
        Iterator<String> s = result.get("een").iterator();
        assertEquals("een", s.next());
        assertEquals("ene", s.next());
        assertEquals("nee", s.next());
        assertFalse(s.hasNext());

    }

    @Test
    public void anagramsBigWordDouble()
    {
        final String bigWord = "zandzeepsodemineraalwatersteenstralen";
        AnagramFinder finder = new AnagramFinder();
        Map<String, Set<String>> result = finder.getAnagrams(bigWord + " " + bigWord + " " + bigWord + " " + bigWord);
        assertEquals(1, result.size());
        assertNotNull(result.get(bigWord));
        assertEquals(1, result.get(bigWord).size());
        assertEquals(bigWord, result.get(bigWord).iterator().next());
    }

    @Test
    public void anagramsBigWordsSameLetters()
    {
        final String bigWord = "zandzeepsodemineraalwatersteenstralen";
        final String shuffledWord = "aaltrloeiazrneretadtsnepeeznsdwemsean";
        Iterator<String> s;
        AnagramFinder finder = new AnagramFinder();
        Map<String, Set<String>> result = finder.getAnagrams(bigWord + " " + shuffledWord + " " + bigWord + " " + shuffledWord);


        assertEquals(2, result.size());

        assertNotNull(result.get(bigWord));
        assertEquals(2, result.get(bigWord).size());
        s = result.get(bigWord).iterator();
        assertEquals(shuffledWord, s.next());
        assertEquals(bigWord, s.next());
        assertFalse(s.hasNext());

        assertNotNull(result.get(shuffledWord));
        assertEquals(2, result.get(shuffledWord).size());
        s = result.get(shuffledWord).iterator();
        assertEquals(shuffledWord, s.next());
        assertEquals(bigWord, s.next());
        assertFalse(s.hasNext());

    }


    @Test
    public void testSplitLength()
    {
        AnagramFinder finder = new AnagramFinder();
        Set<String> result = finder.getWords("Een de het friet baan ik!");
        assertNotNull(result);
        assertEquals(6, result.size());
        Iterator<String> it = result.iterator();
        assertEquals("de", it.next());
        assertEquals("ik", it.next());
        assertEquals("een", it.next());
        assertEquals("het", it.next());
        assertEquals("baan", it.next());
        assertEquals("friet", it.next());

    }

    @Test
    public void lorumIpsum1000test()
    {
        String text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi ac orci cursus, laoreet ligula quis, auctor mi. Quisque eu consectetur eros. Aliquam quis ultrices orci, vel sagittis arcu. Nunc mauris tellus, scelerisque et est ac, mattis congue ex. Sed eu metus vitae tellus condimentum vestibulum non in diam. Curabitur fringilla quam ut lacinia sollicitudin. Curabitur eget tellus eu enim luctus vulputate et quis ipsum. Phasellus et sem vitae nunc tristique vestibulum. Nam vitae lorem nec lectus placerat venenatis sed a sem. Ut a vestibulum felis.\n" +
                "Donec ornare mauris nibh, a mollis lacus rutrum non. Cras aliquam nulla eros, semper dignissim est tincidunt vel. Nunc non tincidunt magna. Donec sodales venenatis arcu sed tempor. Vivamus tellus nibh, venenatis et suscipit id, convallis sed nisi. Etiam rhoncus, mi a suscipit luctus, erat erat porta dolor, sed sagittis urna nisl in libero. Sed sollicitudin, leo et imperdiet luctus, nisl libero volutpat velit, vel malesuada felis diam posuere lectus. Etiam pulvinar convallis orci, consectetur laoreet ante fringilla id. Curabitur vitae pretium mi, eget ultrices erat. Phasellus ac tortor vestibulum velit vestibulum rhoncus. Cras mollis vel odio a semper. Suspendisse vulputate non sapien eget lacinia. Sed mattis turpis sed augue molestie convallis. Nunc a egestas mi.\n" +
                "Cras at laoreet massa, vitae fermentum sem. Morbi sollicitudin euismod vestibulum. Mauris posuere sed lacus in porta. Aenean eget lobortis elit. Nam fermentum, turpis vel sollicitudin consequat, leo nisl pulvinar odio, non ullamcorper enim arcu vitae felis. Ut vel est aliquam lectus euismod interdum a sed urna. Curabitur venenatis ornare orci sit amet egestas. Curabitur eu nisi tincidunt, ultrices lacus eget, posuere tellus. Curabitur a varius neque. Sed quis lorem interdum neque congue consectetur at ac massa. Phasellus faucibus urna sed libero interdum convallis. Nunc vitae arcu elementum, fermentum lorem a, iaculis erat. Quisque at blandit nibh.\n" +
                "Phasellus id ipsum sem. Duis in dolor imperdiet, luctus enim ac, viverra metus. Suspendisse ornare libero non ipsum ornare, sit amet fringilla nisi accumsan. Quisque quis erat eu mauris consequat bibendum sed posuere elit. Aliquam erat volutpat. Integer malesuada felis sit amet nibh laoreet, non rutrum felis lacinia. Fusce in condimentum ligula, at dapibus odio.\n" +
                "Maecenas convallis mollis turpis vel fermentum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Integer pretium, eros vel pretium rhoncus, sem est lacinia lorem, eu aliquet enim velit eu ipsum. Nunc feugiat lectus a libero lobortis interdum. Phasellus non mauris in quam cursus rhoncus. Interdum et malesuada fames ac ante ipsum primis in faucibus. Praesent tincidunt, mi eu dignissim dictum, nulla nisl scelerisque nunc, non porttitor ipsum ipsum nec ex. Nullam iaculis nibh et congue efficitur. Nullam nulla leo, vehicula ac mauris et, dignissim auctor eros. Nunc neque lorem, ultrices ut ligula in, pretium feugiat magna. Aenean posuere in sem at tincidunt. Cras in aliquet leo.\n" +
                "Cras nec tincidunt lacus. Duis interdum sem nibh, in ornare orci egestas id. Proin ullamcorper dictum eros eget efficitur. Quisque metus justo, suscipit vitae nulla sed, pellentesque posuere arcu. Cras cursus lorem et sem iaculis auctor. Aenean aliquet vel libero ac vulputate. Lorem ipsum dolor sit amet, consectetur adipiscing elit.\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent orci libero, iaculis quis pulvinar vitae, porta sit amet ipsum. In finibus, tellus sed dictum efficitur, urna eros aliquam nisi, scelerisque elementum diam eros et mi. Vivamus ac lectus felis. Duis in ipsum non libero tempus volutpat. Aliquam non interdum mauris, nec suscipit nibh. Morbi mollis luctus metus et imperdiet. Suspendisse ultricies aliquam ex, id mollis tellus rhoncus a. Vivamus scelerisque libero augue, et sollicitudin massa condimentum commodo. Nunc in nibh at dolor maximus pellentesque nec eget lorem. Fusce mauris arcu, dapibus et faucibus in, pharetra nec elit.\n" +
                "Integer vel ante vel urna facilisis molestie ut ut felis. Donec molestie leo a lectus molestie placerat. Cras auctor interdum nibh, vitae pretium ante vehicula sit amet. Nulla nec ullamcorper magna. Donec gravida feugiat mauris, eu sagittis sapien maximus nec. Integer gravida sapien in dolor fermentum efficitur. Mauris ut tristique nulla. Sed iaculis ipsum non urna porta, tincidunt placerat tellus gravida. Fusce ultrices libero mauris, quis ultrices sapien vehicula et. Sed vel placerat eros, dapibus gravida felis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Curabitur tincidunt commodo commodo. Praesent libero urna, pretium ut efficitur a, pretium et ante. Donec sed fermentum nibh. Proin congue ex vitae tincidunt viverra.\n" +
                "Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec interdum ornare ligula et semper. Suspendisse volutpat tellus eros, vestibulum iaculis eros pellentesque eu. Curabitur vitae magna neque. Morbi pharetra condimentum fringilla. Vivamus mauris massa, pellentesque ac luctus vitae, imperdiet quis ante. Quisque egestas eros id mi consequat, at elementum massa pellentesque. Phasellus semper tortor risus, a pretium ligula sodales quis. Maecenas velit augue, convallis sit amet pharetra a, tristique eget orci. Quisque posuere in turpis ut maximus. Maecenas in scelerisque dolor. Donec imperdiet orci id ex blandit, congue porta nunc porta. Aliquam interdum justo quis dui tincidunt, vel varius felis hendrerit. Quisque commodo mauris eget orci semper condimentum. Donec a tincidunt mauris, vitae tincidunt massa.\n" +
                "Sed sed est vitae est dapibus rutrum vel et dolor. Fusce iaculis finibus ex. In quis pellentesque tortor, ac lobortis turpis. Cras fringilla sodales laoreet. Duis sed massa ac arcu hendrerit tincidunt in in ligula. Etiam odio ante, aliquam in egestas ac, porttitor nec dolor. Morbi iaculis, tortor quis commodo mollis, nulla odio feugiat eros, eget euismod mi enim sit amet odio. Sed nec placerat erat. Praesent et vehicula massa, sit amet convallis leo. Quisque sed mi justo. Nulla bibendum dolor eget erat venenatis, ac mattis quam tristique. Praesent dui dui, aliquet non mauris vel, scelerisque vulputate metus.\n" +
                "Vivamus vitae velit justo. Duis at orci ut arcu blandit blandit eget vel nunc. Vestibulum quis placerat purus. Maecenas condimentum lorem libero, a vestibulum risus euismod ut. Pellentesque nec ligula et orci varius volutpat. Curabitur laoreet magna ante, a maximus massa facilisis quis. In ultrices ex et metus venenatis, a scelerisque magna vulputate. Morbi consectetur leo vel tincidunt.";
        AnagramFinder finder = new AnagramFinder();
        Map<String, Set<String>> result = finder.getAnagrams(text);
        assertFalse(result.size() >= 1000);
    }
}
