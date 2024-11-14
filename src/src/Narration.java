
public class Narration {

    private String[] dialogues = {
        /*0*/"In a universe governed by a brutal game called the Cosmic Challenge, the main character must navigate a series of encounters with flawed characters while learning the true meaning of love, friendship, and survival. Each character he meets offers valuable lessons but also poses a potential threat as he strives to go back to Earth and confront the dark forces threatening the cosmos. Will he rise above the challenges and find a way to save not only himself but also the stars that guide him? The journey ahead is filled with dangers, but the strength of the heart can light the way.",
        /*1*/ "You wake up to the sound of roosters crowing—a sign of the sun rising as its rays hit your eyes. You hide your face under the blankets to continue sleeping, but a sudden buzz from your phone interrupts you.",
        /*2*/ "You reach for your phone and see a notification on the screen.",
        /*3*/ "You decide to continue sleeping, enjoying a few more minutes of peace. After an hour, your phone’s alarm starts ringing. You turn the alarm off and check your phone. You see an email from a sender named Anonymous with the subject line 'CC. Little Prince'. Out of curiosity, you read the email and see that you are a chosen participant in what they called the 'Cosmic Challenge.'",
        /*4*/ "You want to know more about the   game, so you scan the QR code attached to the email. It redirects to a website with a timer countdown (00:01:10) and the text 'GAME STARTS AT'. In a panic, you close the website and delete the email. And suddenly...",
        /*5*/ "Where am I?",
        /*6*/ "Hello there, little one. I'm Sira, your virtual companion throughout your journey. Welcome to the Cosmic Challenge. The Cosmic Challenge is a high-stakes, intergalactic competition that draws beings from diverse planets into a relentless battle for survival. This daunting arena serves not only as a test of physical strength and slyness but also as a profound journey of self-discovery and exploration of the nature of existence. You start with 100 HP in the challenge. Throughout, you can collect petals by defeating minions—each rose collected will gain you experience (+EXP). To unlock new planets, you’ll need to gather keys. You can view your inventory anytime to check your items and change your skills when you acquire new ones. Make wise choices, as they may be crucial for your survival. Good luck, and remember: The little prince is watching.",
        /*7*/ "Oh, how beautiful you are.",
        /*8*/ "I’m only half awake. I’m sorry, I’m disheveled.",
        /*9*/ "You’re perfect.",
        /*10*/ "They shared their first sunrise and sunset. But in the cold nights, the rose began to torment little by little. They loved each other, but they were both too young to know how to love.",
        /*11*/ "Hi, how are you?", //rose
        /*12*/ "Oh, hi there traveler. I’m fine, but sooner or later, my existence will be gone. My prince, oh, take care of him for me, please.",
        /*13*/ "Hi, who are you?",  //prince
        /*14*/ "I’m the Little Prince.",
        /*15*/ "What are you doing here?",
        /*16*/ "I govern this planet. I am a traveler of my own. Do you want to join my expedition for tomorrow?",
        /*17*/ "Yes, but what are we doing?",
        /*18*/ "I want to save my rose. I’m going to other planets to seek advice. Help me—I really want to save my rose. We will travel tomorrow. The cosmos may look so nice, but beware: dangers await us. Rest for now, traveler.",
        /*19*/ "You look for the little prince but you can’t find him anywhere. You see footprints and follow them hoping to see the prince but he is nowhere to be found. Instead, you saw a rocket ship, with a print saying Cosmo C.",
        /*20*/ "5...4...3...2...1…",

        //chapter1
        /*21*/ "Speaker: You landed in \"In Honor of the Flaring Statuette.\" Explore the world and conquer it. ",
        /*22*/ "Main Character: Conquer?",
        
        // explore, key acquired
        /*23*/ "Lamplighter: Ah, you, you dare fight Cassius! Excellent! What brings you to my little corner of the universe?",
        /*24*/ "Main Character: I was wandering through the stars, seeking companionship. Do you light the way for all travelers?",
        /*25*/ "Lamplighter: Indeed! Each sunset brings a new flame to ignite. But tell me, what do you seek among the stars?",
        /*26*/ "Main Character: I seek understanding and adventure. Would you join me on my journey?",
        /*27*/ "Lamplighter: I would be honored! Together, we can delve into the mysteries of the cosmos. But remember, each light we create guides others too.",
        /*28*/ "Main Character: That sounds like the ultimate adventure! Let’s light the way for those who wander.",
        /*29*/ "Cassius: Oh! Hi there, traveler. I bet you’ve heard the news, yes? Why, yes! I am the fairest of them all. For I am Cassius, whose radiance burns everything it touches across the cosmos!",
        /*30*/ "Main Character: Uh, hello? I don’t know why I am here but have you seen the little prince? \r\n",
        /*31*/ "Cassius: Oh? The little prince? I do not know any little prince. Is he handsome? Is he smarter? Is he better than me? Well, of course not. If there is someone you should look for, it is me. I can be your Prince in your stead.",
        /*32*/ "Lamplighter: (whispers) He’s full of himself. Crazy.",
        /*33*/ "Main Character: Uh, no thank you, but I appreciate the offer. By any chance, do you know anything about the Cosmo Challenge? I was suddenly brought here. I am from planet Earth.",
        /*34*/ "Cassius: Cosmo Challenge? You are *Enter your name*?",
        /*35*/ "Main Character: Yes. That’s me.",
        /*36*/ "Lamplighter: And I am his great companion, Lamplighter.",
        /*37*/ "Cassius: Planet Earth, huh? I used to live there. What a beautiful planet, it used to be perfect for me. But the people, they are cruel. They throw things at me and tell me words that make me bleed physically and emotionally. They said I’m unlikeable, they don’t admire me. After that, I realize I can be somewhere where my beauty can be seen. Cassius: Do you admire me?",
        /*38*/ "Main Character: Admire?",
        /*39*/ "Cassius: To admire means that you regard me as the most handsome, the best-dressed, the richest, and the most intelligent man on this planet.",
        /*40*/ "Main Character: But you are all alone on this planet, how can you say that you are the most of the most of them all?",
        /*41*/ "Cassius: Alone? How dare you say that I am alone. How dare you doubt my rizz. Of course, I am. I AM THE MOST OF MOST OF THEM ALL.",
        /*42*/ "Lamplighter: Oh no..",

        
        //lose scenario after vainmain
        
        /*43 */"Narration: You lose. The vain man commands you to clap and praise him all day and night.",

        // win scaneior vain man
        /*44 */"Narration: With a surge of light, the main character creates a radiant shield that deflects Cassius’s illusions back at him. The dazzling reflections reveal Cassius’s true self—a lonely figure seeking validation.",
        "Cassius: What… what is this?",
        "Main Character: This is who you really are, Cassius. You don’t need all this grandeur to be worthy.",
        "Cassius: But… without my crown and my applause… who am I?",
        "Main Character: You are still you. A person who deserves love and friendship just like anyone else. You don’t have to be alone anymore.",
        "Cassius: Perhaps I’ve been looking for validation in all the wrong places…",
        "Main Character: It’s okay, it’s not your fault. You were just blinded for a while. Find your people  And remember, it’s okay to be vulnerable. That’s where real strength lies. I know how you wanted for others to see you but all these praises will never match how worthy you really are. Start by loving yourself first. I can be your friend. You are worthy and wanted. True value comes from who we are inside and not how others see us. But, I want to save my friend, first, so help me take me to him.",
        /*51 */"Cassius: Thank you so much for your kind words. Here is a map as a gratitude, this might help you from finding the little prince.",
        
        //starts chapter 2 the drunkard
        /*52 */"Speaker: Congratulations! You are now in stage 2. How brave of you to trick Cassius but beware this might be the end of your journey.",
        
        /*53 */"Narrator: You arrived at Boozara where The Drunkard resides.",
        "Narration: As the main character stumbled through the haze of\r\n" + //
                        "dizziness, the world around them blurred. In that surreal moment,\r\n" + //
                        "a figure emerged from the fog: the Little Prince. With his golden\r\n" + //
                        "hair and curious gaze, he seemed to glow against the shadows. The\r\n" + //
                        "Prince smiled, his presence grounding the chaos, inviting the\r\n" + //
                        "character to look beyond the confusion. In that instant,\r\n" + //
                        "everything felt possible again, as if the weight of the world\r\n" + //
                        "lifted, if only for a fleeting moment.",
        "Main Character: I feel so dizzy.\r",
        "Little Prince: The fox… find the fox",
        "Main Character: Little Prince? Hello? Are you there?",
        "Narration: You searched for the Little Prince for hours, calling\r\n" + //
                        "out but hearing only silence. With each passing moment, hope faded\r\n" + //
                        "as the landscape grew emptier.\r",
        "Main Character: He said something about the fox, I need to find\r\n" + //
                        "him. He might lead me to the Prince.\r",
        /*60 */"Narration: The Little Prince wandered through the vast planet\r\n" + //
                        "calling out for the Fox.",
        
        //acquiring fox
        /*61 */"Main Character: Fox! There you are! I’ve been looking for you!\r",
        "Fox: Who are you? I don’t recognize you.\r",
        "Main Character: I’m a traveler.\r",
        "Fox: I’ve seen many travelers. What makes you different?\r",
        "Main Character: I seek friendship and understanding.\r",
        "Fox: Friendship takes time. What have you learned on your journey?\r",
        "Main Character: I’ve learned that connections are what make us\r\n" + //
                        "special.\r",
        "Fox: Interesting. But how can I trust you?\r",
        "Main Character: Trust is built slowly, through time and shared\r\n" + //
                        "moments.\r",
        "Fox: Then let’s start. What do you wish to share with me?\r",
        "Main Character: I want to show you the beauty in the stars, how\r\n" + //
                        "simple and complex everything is.\r",
        /*72 */"Fox: Perhaps that’s a good place to begin. Let’s see where this\r\n" + //
                        "journey takes us.\r",
        

        //encounter drunkard
        /*73 */"Main character: What are you doing here?\r",
        "Drunkard: I am drinking.\r",
        "Main character: Why? Why are you drinking?\r",
        "Drunkard: In order to forget.\r",
        "Main Character: Forget what?\r",
        "Drunkard: To forget that I am ashamed.\r",
        "Main Character: Ashamed of what?\r",
        "Drunkard: Ashamed of drinking!\r",
        "Main Character: Grown-ups really are very, very odd.\r",
        /*82 */"Drunkard: Why! Why did you uncover the truth! I just want to live\r\n" + //
                        "peacefully…\r",
        
        //drunkard lose scenario
        /*83 */"Narration: In a quiet moment, the Little Prince confronted the\r\n" + //
                        "Drunkard, hoping to bring clarity. But the Drunkard, lost in his\r\n" + //
                        "haze, only spiraled deeper into despair. The Prince, filled with\r\n" + //
                        "innocence, felt the weight of his words fade as he realized that\r\n" + //
                        "some souls are too entangled in their sorrow to be saved.\r\n" + //
                        "Defeated, he walked away, carrying the heavy truth that not every\r\n" + //
                        "battle can be won.",
        
        //drunkard win scenario
        /*84 */"Main Character: My dear friend, drinking is not the solution to\r\n" + //
                        "your problems. The journey must feel hell for you to act this way\r\n" + //
                        "but aren’t you supposed to continue striving for you to experience\r\n" + //
                        "heaven? But tell me, why do you drink so much?",
        "Drunkard: Ah, my dear friend! To forget, of course! To forget the\r\n" + //
                        "weight of the world and the absurdity of it all! (He raises the\r\n" + //
                        "bottle high.) Cheers to you and your noble quest!\r",
        "Narration: The drunkard takes a swig from the bottle and offers it\r\n" + //
                        "to the main character.\r",
        "Main Character: I appreciate the offer, but I think I’ll stick to\r\n" + //
                        "clear thoughts for now.\r",
        "Drunkard: Clear thoughts? What a curious notion! You see, my\r\n" + //
                        "friend, grown-ups are strange creatures indeed. They drink to\r\n" + //
                        "forget their troubles, yet they forget what truly matters! Look\r\n" + //
                        "around!",
        "Main Character: Yes, they seem so caught up in their own lives.\r\n" + //
                        "Like that man over there counting stars as if they were coins…",
        "Drunkard: Exactly!\r",
        "Main Character: And what about you? Are you not also trying to\r\n" + //
                        "escape?",
        "Drunkard: Touché! But my escape is a celebration of life! A toast\r\n" + //
                        "to friendship and dreams! To dreams that take us far away—to\r\n" + //
                        "planets unknown and adventures untold!",
        "Narration: You giggle at Cassius’s antics, feeling lighter despite\r\n" + //
                        "the weight of his mission.",
        "Main Character: Perhaps there’s wisdom in your folly. Maybe we\r\n" + //
                        "should celebrate every small victory along our journey!",
        "Drunkard: And remember, my friend, while we may stumble through\r\n" + //
                        "this life like drunken stars, it’s our hearts that guide us home.\r",
        "Main Character: Yes, even stars have their own paths. I must find\r\n" + //
                        "mine.",
        /*97 */"Drunkard: And I will help you find it. But first… let’s enjoy this\r\n" + //
                        "moment! Here drink this elixir.\r",
        

        //chapter 3 - king
        /*98 */"Narrator: You found yourself, with lamplighter and fox, flying to\r\n" + //
                        "another planet and landed on some planet that seems empty.",
        "Sira: Do obey who resides here.\r",
       /*100 */ "Main character: Why?\r",

        //king encounter
        /*101 */"Narrator: As you stumbled on a royal door, you opened it and found\r\n" + //
                        "someone sitting on the throne. There sits upon the King, wearing a\r\n" + //
                        "crown holding a scepter.\r",
        "Main character: Wow, this place is… empty. Are you the one in\r\n" + //
                        "charge here?",
        "King: In charge? I am more than in charge. Everything here bends\r\n" + //
                        "to my will.\r",
        "Main Character: So, just the throne and the dirt?\r",
        "King: Exactly. But no matter! What is a kingdom without absolute\r\n" + //
                        "authority? Tell me why are you here? Have you come to pledge your\r\n" + //
                        "loyalty or seek my commands?\r",
        "Main Character: I’m on an adventure, I'm looking for someone.",
        "King: Maybe I can help, my commands are the finest! I can command\r\n" + //
                        "the sun to rise, the stars to shine, the winds to cease! Though…\r\n" + //
                        "they often obey only when it pleases them.",
        "Main Character: So, you “command” things that already happen?\r",
        "King: … But still my commands are unquestionable! And as my loyal\r\n" + //
                        "subject, you must respect me! I command you to bow.\r",
        "Main character: Bow? I don’t bow to anyone.\r",
        /*111 */"King: You defy me? No matter. I shall command you to stay still\r\n" + //
                        "then.",
        
        //king lose scenario
        /*112 */"King: Pathetic. Did you truly believe you could defy me? There are\r\n" + //
                        "no challengers to my throne. Only subjects who obey.",
        "Narrator: The King's laughter reverberates through the hall as your\r\n" + //
                        "vision darkens, his shadow looming over you like a suffocating\r\n" + //
                        "cloud. Your adventure ends here, not with a grand victory, but\r\n" + //
                        "under the heel of the tyrant.",
        /*114 */"Narrator: The scepter is raised high above you. The last thing you\r\n" + //
                        "see before everything fades to black is the King's twisted smile,\r\n" + //
                        "knowing he’s won yet again.",
        
        //king win scenario
        /*115 */"Narrator: You defeated the King. The King, bruised and exhausted,\r\n" + //
                        "is on his knees in front of you, his scepter lying shattered on\r\n" + //
                        "the ground.\r",
        "King: Impossible… How could you… defeat a king? You… defied my\r\n" + //
                        "commands! No one defies a king! No one defies me.",
        "Main character: I guess your commands don’t work when you’re not on\r\n" + //
                        "your throne. You were too busy bossing everything around to see\r\n" + //
                        "what you’re lacking. I have a fox by my side while you are alone.\r\n" + //
                        "A king needs its subjects.",
        "King: You think your little fox and a lamplighter makes you\r\n" + //
                        "stronger? It’s nothing but a creature! A king stands alone, above\r\n" + //
                        "all, commanding respect!",
        "Main character: But that’s exactly it, Your Majesty. A king\r\n" + //
                        "standing alone has no one to share their burdens with. The fox\r\n" + //
                        "understands me; it teaches me about friendship and loyalty. You\r\n" + //
                        "may command the stars, but they don’t answer to loneliness.",
        "King: Loneliness? I am a king! I am meant to be alone at the top,\r\n" + //
                        "revered, respected… But... I never thought of it that way…\r",
        "Main Character: Maybe you’ve built your throne too high, Your\r\n" + //
                        "Majesty. Being above everyone doesn’t make you wise; it isolates\r\n" + //
                        "you. A true ruler brings others together. This world craves\r\n" + //
                        "connection, not commands. Imagine how much richer your kingdom\r\n" + //
                        "could be if you invited others to share it with you.\r",
        "King: I... I wanted to be admired. I wanted to be powerful. But\r\n" + //
                        "now, all I feel is emptiness. Could it be that I pushed everyone\r\n" + //
                        "away?",
        "Main Character: You have the chance to change that. You can be more\r\n" + //
                        "than a king; you can be a friend, a leader who listens. Start by\r\n" + //
                        "getting to know your subjects. Ask them what they need, what they\r\n" + //
                        "want. You might be surprised by what you discover!\r",
        "King: You... you truly believe I can change? Perhaps a king should\r\n" + //
                        "not fear change, but embrace it!",
        "Main Character: Exactly! Every adventure starts with a single step.\r\n" + //
                        "And maybe, just maybe, it begins with you reaching out instead of\r\n" + //
                        "ruling down.\r",
        "King: Then I shall not be the king who commands! I will be the\r\n" + //
                        "king who listens. I will find my subjects and learn from them!\r\n" + //
                        "This world will be filled with friendship and understanding. Thank\r\n" + //
                        "you, curious one. You’ve taught me more than I ever learned on my\r\n" + //
                        "throne.",
        /*127 */"Main Character: Remember, Your Majesty, a curious heart is the best\r\n" + //
                        "compass you can have. Good luck\r",
        
        //chapter 4
        /*128 */"Sira : Congratulations! You have defeated the King. You have\r\n" + //
                        "overcome your deepest trauma in life. The key will bring you back\r\n" + //
                        "to Earth.",
        "Main Character: Earth? I haven’t found the little prince yet.\r",
        /*130 */"Sira: You’ll be teleported to Earth in 3..2..1\r",




    };

    public String getDialogue(int index) {
        if (index >= 0 && index < dialogues.length) {
            return dialogues[index];
        }
        return "Dialogue not found.";
    }

    public int getDialogueCount() {
        return dialogues.length;
    }
}
