public class Narration {
    // Array to hold different narration strings
private String[] dialogues = {
        /*0*/"In a universe governed by a brutal game called the Cosmic Challenge, the main character must navigate a series of encounters with flawed characters while learning the true meaning of love, friendship, and survival. Each character he meets offers valuable lessons but also poses a potential threat as he strives to go back to Earth and confront the dark forces threatening the cosmos. Will he rise above the challenges and find a way to save not only himself but also the stars that guide him? The journey ahead is filled with dangers, but the strength of the heart can light the way.",
        /*1*/ "You wake up to the sound of roosters crowing—a sign of the sun rising as its rays hit your eyes. You hide your face under the blankets to continue sleeping, but a sudden buzz from your phone interrupts you.",
        /*2*/ "You reach for your phone and see a notification on the screen.",
        /*3*/ "You decide to continue sleeping, enjoying a few more minutes of peace. After an hour, your phone’s alarm starts ringing. You turn the alarm off and check your phone. You see an email from a sender named Anonymous with the subject line 'CC. Little Prince'. Out of curiosity, you read the email and see that you are a chosen participant in what they called the 'Cosmic Challenge.'",
        /*4*/ "You want to know more about the game, so you scan the QR code attached to the email. It redirects to a website with a timer countdown (00:01:10) and the text 'GAME STARTS AT'. In a panic, you close the website and delete the email. And suddenly...",
        /*5*/ "Where am I?",
        /*6*/ "Hello there, little one. I'm Sira, your virtual companion throughout your journey. Welcome to the Cosmic Challenge. The Cosmic Challenge is a high-stakes, intergalactic competition that draws beings from diverse planets into a relentless battle for survival. This daunting arena serves not only as a test of physical strength and slyness but also as a profound journey of self-discovery and exploration of the nature of existence. You start with 100 HP in the challenge. Throughout, you can collect petals by defeating minions—each rose collected will gain you experience (+EXP).",
        /*7*/ "To unlock new planets, you’ll need to gather keys. You can view your inventory anytime to check your items and change your skills when you acquire new ones. Make wise choices, as they may be crucial for your survival. Good luck, and remember: The little prince is watching.",
        /*8*/ "Oh, how beautiful you are.",
        /*9*/ "I’m only half awake. I’m sorry, I’m disheveled.",
        /*10*/ "You’re perfect.",
        /*11*/ "They shared their first sunrise and sunset. But in the cold nights, the rose began to torment little by little. They loved each other, but they were both too young to know how to love.",
        /*12*/ "Hi, how are you?",
        /*13*/ "Oh, hi there traveler. I’m fine, but sooner or later, my existence will be gone. My prince, oh, take care of him for me, please.",
        /*14*/ "Hi, who are you?",
        /*15*/ "I’m the Little Prince.",
        /*16*/ "What are you doing here?",
        /*17*/ "I govern this planet. I am a traveler of my own. Do you want to join my expedition for tomorrow?",
        /*18*/ "Yes, but what are we doing?",
        /*19*/ "I want to save my rose. I’m going to other planets to seek advice. Help me—I really want to save my rose. We will travel tomorrow. The cosmos may look so nice, but beware: dangers await us. Rest for now, traveler.",
        /*20*/ "You look for the little prince but you can’t find him anywhere. You see footprints and follow them hoping to see the prince but he is nowhere to be found. Instead, you saw a rocket ship, with a print saying Cosmo C.             ",
        /*21*/ "5...4...3...2...1…",
        /*22*/ "Speaker: You landed in In Honor of the Flaring Statuette. Explore the world and conquer it. ",
        /*23*/"Main Character: Conquer?",

   
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
