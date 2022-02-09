package Model;

import java.security.KeyPair;
import java.util.*;

public class PhraseSpeakerMap
{
    public List<String> phrase = null;
    public List<Integer> speakerFrequency = null;
    private Map<String, Integer> speakerLocationMap = new HashMap<>();


    public PhraseSpeakerMap(List<String> phrase)
    {
        this(phrase, new ArrayList<>());
    }
    public PhraseSpeakerMap(List<String> phrase, List<Integer> speakerFrequency)
    {
        this.phrase = phrase;
        this.speakerFrequency = speakerFrequency;
        for (String currSpeaker : speakers)
        {
            speakerFrequency.add(0);
            speakerLocationMap.put(currSpeaker, speakerFrequency.size()-1);
        }
    }

    public void incrementCount(String speaker)
    {
        try
        {
            if (!speakerLocationMap.containsKey(speaker))
            {
                System.out.println("\nNEW SPEAKER: " + speaker);
                speakers.add(speaker);
                speakerLocationMap.put(speaker, speakers.size());
                speakerFrequency.add(0);
            }
            speakerFrequency.set(speakerLocationMap.get(speaker), speakerFrequency.get(speakerLocationMap.get(speaker)) + 1);
        }
        catch (NullPointerException e)
        {
            System.out.println(speaker);
            throw e;
        }
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("PhraseSpeakerMap{\n");
        sb.append("phrase=");
        for (String s : phrase)
            sb.append(" ").append(phrase);
        sb.append("\n, speakerFrequency=");
        sb.append('}');
        return sb.toString();
    }


    public List<String> speakers = Arrays.asList(
                    "SATAN",
                    "JARED 1",
                    "BROTHER OF JARED",
                    "GODHEAD",
                    "DAUGHTER OF JARED",
                    "AKISH",
                    "JARED 2",
                    "JACOB( ISRAEL )",
                    "JOSEPH",
                    "OT REVELATORY GODHEAD",
                    "MOSES",
                    "JOSHUA",
                    "UNIDENTIFIED ANCIENT SCRIPTURE WRITER",
                    "ISAIAH",
                    "MICAH",
                    "ZENOCK",
                    "ZENOS",
                    "ETHER",
                    "PEOPLE WHO FEARED SHIZ",
                    "LEHI",
                    "NEPHI 1",
                    "CELESTIAL BEINGS",
                    "LAMAN AND LEMUEL",
                    "LABAN",
                    "SARIAH",
                    "DAUGHTERS OF ISHMAEL",
                    "JACOB",
                    "SHEREM",
                    "MALACHI",
                    "ENOS",
                    "JAROM",
                    "OMNI",
                    "AMARON",
                    "CHEMISH",
                    "ZENIFF",
                    "ABINADOM",
                    "ABINADI",
                    "NOAH",
                    "WICKED PRIESTS",
                    "ALMA 1",
                    "PEOPLE OF LIMHI",
                    "LIMHI",
                    "LAMANITE KING",
                    "GIDEON",
                    "AMALEKI",
                    "BENJAMIN",
                    "KING BENJAMINS PEOPLE",
                    "AMMON 1",
                    "MOSIAH",
                    "ALMA 2",
                    "PEOPLE OF MOSIAH",
                    "PEOPLE OF ZARAHEMLA 1",
                    "AMMON 2",
                    "SERVANTS OF LAMONI",
                    "LAMONI",
                    "LAMANITE QUEEN",
                    "SOME PEOPLE OF LAMONI",
                    "LAMONIS FATHER",
                    "AARON",
                    "AN AMALEKITE",
                    "SERVANTS OF LAMONIS FATHER",
                    "ANTI-NEPHI-LEHI",
                    "ZERAM AMNOR MANTI LIMHER",
                    "AMULEK",
                    "PEOPLE OF AMMONIHAH",
                    "ZEEZROM",
                    "ANTIONAH",
                    "CHIEF JUDGE",
                    "LAWYERS JUDGES PRIESTS TEACHERS OF AMMONIHAH",
                    "PEOPLE OF ZARAHEMLA 2",
                    "GIDDONAH",
                    "KORIHOR",
                    "NEPHIHAH",
                    "UNBELIEVERS",
                    "MORONI 1",
                    "POOR ZORAMITE",
                    "ZERAHEMNAH",
                    "ZORAMITE PRAYER",
                    "SOLDIER",
                    "HELAMAN 1",
                    "PEOPLE OF MORONI",
                    "SERVANTS OF AMALICKIAH",
                    "AMALICKIAH",
                    "STRIPLING WARRIORS",
                    "AMMORON",
                    "GID",
                    "NEPHITE SPIES",
                    "LAMANITE GUARDS",
                    "LAMAN 2",
                    "PAHORAN",
                    "SERVANT OF HELAMAN",
                    "HELAMAN 2",
                    "NEPHI 2",
                    "LAMANITES IN THE CITY OF NEPHI",
                    "AMINADAB",
                    "NEPHITE JUDGES",
                    "LAMANITES AT NEPHIS GARDEN",
                    "NEPHIS BRETHREN",
                    "FIVE NEPHITES",
                    "NEPHITES AT JUDGEMENT-SEAT 1",
                    "NEPHITES AT JUDGEMENT-SEAT 2",
                    "NEPHITE DISPUTERS",
                    "CHIEF JUDGES AND LEADERS DURING FAMINE",
                    "PEOPLE OF ZARAHEMLA 3",
                    "SAMUEL",
                    "PEOPLE AT WALL OF ZARAHEMLA",
                    "HARDENED NEPHITES AND LAMANITES",
                    "PEOPLE IN 92ND YEAR",
                    "GIDDIANHI",
                    "LACHONEUS",
                    "PEOPLE OF GIDGIDDONI",
                    "GIDGIDDONI",
                    "JOHN THE BAPTIST",
                    "PEOPLE IN DARKNESS 1",
                    "PEOPLE IN DARKNESS 2",
                    "CHRIST IN AMERICA",
                    "PEOPLE OF ZARAHEMLA AT CHRISTS VISIT",
                    "NEPHITE DISCIPLES",
                    "THREE NEPHITES",
                    "AMMARON",
                    "MORMON",
                    "MORONI 2",
                    "ANGELS",
                    "SERVANT OF LAMONI"
    );

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhraseSpeakerMap that = (PhraseSpeakerMap) o;
        return Objects.equals(phrase, that.phrase) && Objects.equals(speakerFrequency, that.speakerFrequency) && Objects.equals(speakerLocationMap, that.speakerLocationMap) && Objects.equals(speakers, that.speakers);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(phrase, speakerFrequency, speakerLocationMap, speakers);
    }
}
