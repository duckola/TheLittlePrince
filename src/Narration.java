public class Narration {
    // Array to hold different narration strings
private String[] dialogues = {
        /*0*/"In a universe governed by a brutal game called the Cosmic Challenge, the main character must navigate a series of encounters with flawed characters while learning the true meaning of love, friendship, and survival. Each character he meets offers valuable lessons but also poses a potential threat as he strives to go back to Earth and confront the dark forces threatening the cosmos. Will he rise above the challenges and find a way to save not only himself but also the stars that guide him? The journey ahead is filled with dangers, but the strength of the heart can light the way.",
        /*1 */ "You wake up to the sound of roosters crowing—a sign of the sun rising as its rays hit your eyes. You hide your face under the blankets to continue sleeping, but a sudden buzz from your phone interrupts you from doing so.",
        /*2 */ "You reach for your phone and see a notification on the screen.",
        /*3*/ "You decide to continue sleeping, enjoying a few more minutes of peace. After an hour, your phone’s alarm started to ring. You turn the alarm off and check your phone. You saw an email from a sender named Anonymous with the subject line “CC. Little Prince”. Out of curiosity, you read the email and saw that you are the chosen participant in what they called the “Cosmic Challenge\".",
        /*4 */ "You wanted to know more about the game so you scan the QR code attached to it. It redirects to a website with a timer countdown (00: 01: 10) and the text “GAME STARTS AT”. In a panic, you close the website and delete the email sent to you. And suddenly…",
        /*5 */ "Main Character: Where am I?",
        /*6 */ "Speaker: Hello there little one, I'm Sira, your virtual companion throughout your journey. Welcome to the Cosmic Challenge. The Cosmic Challenge is a high-stakes, intergalactic competition that draws beings from diverse planets into a relentless battle for survival. This daunting arena serves not only as a test of physical strength and slyness but also as a profound journey of self-discovery and exploration of the nature of existence. You have 100 hp at the start of the challenge. Throughout the challenge, you can collect petals by defeating minions—each rose collected will gain you experience (+EXP).",
        /*7 */ "To unlock new planets, you’ll need to gather keys. You can view your inventory anytime to check your items and change your skills when you acquire new ones. Make wise choices, as they may be crucial for your survival. Good luck, and remember: The little prince is watching.",
        /*8 */ "Little Prince: Oh, how beautiful you are?",
        /*9 */ "Rose: I’m only half awake, I’m sorry, I’m disheveled.",
        /*10*/ "Little Prince: You’re perfect.",
        /*11*/ "Narration: They shared their first sunrise and sunset. But in the cold nights, the rose began to torment little by little. They love each other but they are both too young to know how to love.",
        /*12*/ "Main Character: Hi, how are you?",
        /*13*/ "Rose: Oh hi there traveler. I’m fine but sooner or later, my existence will be gone. My prince, oh, take care of him for me, please."
    };

    

    // Method to get a dialogue by index
    public String getDialogue(int index) {
        if (index >= 0 && index < dialogues.length) {
            return dialogues[index];
        }
        return "Dialogue not found.";
    }

    // Method to get the total number of dialogues
    public int getDialogueCount() {
        return dialogues.length;
    }
}
