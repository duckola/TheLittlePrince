
import java.util.Random;

public class ThoughtLibrary {

    private String[] thoughts;

    // Constructor that initializes the expanded array of inner thoughts
    public ThoughtLibrary() {
        thoughts = new String[]{
            // Thoughts about the journey and the challenge
            "I wonder if I’ll ever return home...",
            "These planets feel so empty, yet they're filled with mystery.",
            "I miss the days when life felt simple. Now everything is uncertain.",
            "Is this what the Cosmic Challenge is all about? Endless trials...",
            "What am I really searching for? Is it the Little Prince, or something deeper?",
            "I need to stay strong... but this journey feels so long.",
            "Every step forward feels like a step further from who I used to be.",
            "Sometimes, I feel like I'm not the same person anymore...",
            "I can't afford to lose hope now. Too much is at stake.",
            // Reflections on the Little Prince and the journey
            "I remember the Little Prince’s stories... his innocence was so pure.",
            "Why was the Little Prince taken? There must be more to this.",
            "The stars above remind me of the Little Prince’s words. Are they watching over me?",
            "The Little Prince spoke of responsibility... am I truly responsible enough for this?",
            "The Prince always saw the beauty in things. I wonder if I still can...",
            "What if the Little Prince knew this was going to happen all along?",
            // Reflective and introspective thoughts
            "Sometimes, I feel like I’m walking in circles... Is there really an end to this?",
            "There’s a strange beauty in solitude... but it’s also terrifying.",
            "I keep thinking about what I’ve left behind. Was leaving the right choice?",
            "I’m beginning to realize... this journey isn't just about survival.",
            "Am I ready to face the final challenge? What if I fail?",
            "It feels like every step I take brings me closer to some terrible truth.",
            "What does it mean to truly survive? Is it just physical, or something more?",
            "The silence here is deafening. It’s as if the universe itself is holding its breath.",
            "Why do I feel like I'm not alone? Even in solitude, there's an unseen presence.",
            // Philosophical thoughts on life and the planets
            "These planets... they each tell a story, but what’s the lesson I’m supposed to learn?",
            "Maybe the true challenge isn’t physical. Maybe it’s the battle within myself.",
            "Each planet feels like a reflection of who I am, or who I used to be.",
            "Is it just me, or do the stars seem closer now? Almost like they're calling out to me.",
            "What if this entire journey is nothing but an illusion? A trick of the mind?",
            "It’s strange... the more I explore, the more questions I have. Yet, fewer answers.",
            "How many more trials must I face before I can return to where I belong?",
            // More emotional or dramatic inner dialogue
            "I miss home. I miss the people I’ve left behind.",
            "Am I doing this for myself... or for something greater?",
            "This challenge feels like it’s changing me. I’m not sure if that’s good or bad.",
            "Every defeat chips away at my will. But I can't let it break me.",
            "It feels like the universe is testing me. But why? What do I have to prove?",
            "The memories... they're starting to fade. I need to hold onto something real.",
            "I can't afford to stop now. Not when I’m this close.",
            // Doubt and uncertainty
            "What if I’m not strong enough to finish this journey?",
            "Is this challenge really worth all the suffering?",
            "I keep asking myself... am I really making any progress?",
            "I’ve never felt so lost, even though the path is right in front of me.",
            "Is the end of this journey what I hope it to be... or something else entirely?",
            "What if this journey has no end? What if it’s just a loop of endless trials?",
            "Sometimes I wonder... what if I was never meant to make it this far?",
            "There’s a darkness in me that I’ve been avoiding. Maybe it's time to face it."
        };
    }

    // Method to return a random inner thought
    public String getRandomThought() {
        Random random = new Random();
        int index = random.nextInt(thoughts.length);  // Get a random index from the array
        return thoughts[index];
    }
}
