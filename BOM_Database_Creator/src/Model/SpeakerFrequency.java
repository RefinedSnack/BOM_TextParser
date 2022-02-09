package Model;

import java.util.*;

public class SpeakerFrequency
{
    private static Map<String, Integer> speakersIndexes = new HashMap<>(); //maintain one of these for the complete class
    private static List<String> speakers = new ArrayList<>(); //maintain one of these for the complete class
    private List<Integer> frequency = new ArrayList<>(); //unique to the instance

    public SpeakerFrequency()
    {
    }

    public SpeakerFrequency(List<String> possibleSpeakers)
    {
        for (String speaker : possibleSpeakers)
            addSpeaker(speaker);
    }
    public void incrementSpeakerCount(String speaker)
    {
        int index = addSpeaker(speaker);
        updateCountSize();
        frequency.set(index, frequency.get(index)+1);

    }

    public static int addSpeaker(String speaker)
    {
        String speakerUppercase = speaker.toUpperCase(Locale.ROOT);
        if (!speakersIndexes.containsKey(speakerUppercase))//speaker is not already present
        {
            speakersIndexes.put(speakerUppercase, speakers.size());
            speakers.add(speaker);
        }
        return speakersIndexes.get(speakerUppercase);
    }

    private void updateCountSize()
    {
        while (frequency.size() < speakers.size())
            frequency.add(0); //grow list to get to the size of the speakers
    }

    public List<Integer> getCounts()
    {
        updateCountSize();
        return frequency;
    }

    public List<Integer> getFrequency()
    {
        return getCounts();
    }
    public static List<String> getSpeakers()
    {
        return speakers;
    }
}
