package Model;

import java.util.List;
import java.util.Map;

public class PhraseSpeakerMap
{
    public List<String> phrase = null;
    public Map<String, Integer> speakerFrequency = null;

    public PhraseSpeakerMap(List<String> phrase, Map<String, Integer> speakerFrequency)
    {
        this.phrase = phrase;
        this.speakerFrequency = speakerFrequency;
    }

    public void incrementCount(String speaker)
    {
        if (!speakerFrequency.containsKey(speaker))
        {
            speakerFrequency.put(speaker, 0);
        }
        speakerFrequency.put(speaker, speakerFrequency.get(speaker) + 1);
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("PhraseSpeakerMap{\n");
        sb.append("phrase=");
        for (String s : phrase)
            sb.append(" ").append(phrase);
        sb.append("\n, speakerFrequency=");
        for(String key : speakerFrequency.keySet())
        sb.append(key).append(": ").append(speakerFrequency.get(key)).append("\n");
        sb.append('}');
        return sb.toString();
    }
}
