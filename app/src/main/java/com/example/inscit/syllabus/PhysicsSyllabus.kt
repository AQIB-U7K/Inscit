package com.example.inscit.syllabus

import com.example.inscit.models.Lang

object PhysicsSyllabus {
    fun getExplanations(lang: Lang): List<String> {
        return when (lang) {
            Lang.EN -> listOf(
                "Motion is a change in position over time. Scalars like Distance and Speed describe how far and fast an object moves, while Vectors like Displacement and Velocity include direction. Acceleration measures how quickly velocity changes, providing a complete picture of an object's kinematics.",
                "Forces are pushes or pulls that change motion. Newton's Three Laws define this: 1) Inertia (objects resist changes in motion), 2) F=ma (force equals mass times acceleration), and 3) Action-Reaction (every force has an equal and opposite counter-force).",
                "Work is done when a force moves an object. Energy is the capacity to do work, existing as Kinetic (motion) or Potential (stored/position). Power measures the rate of doing work. The law of Conservation of Energy states that energy cannot be created or destroyed, only transformed.",
                "Light behaves as both a wave and a particle. Reflection occurs when light bounces off surfaces like Mirrors, following the law where the angle of incidence equals the angle of reflection. This principle allows us to see objects and form images in optical devices.",
                "Heat is the transfer of thermal energy due to temperature differences. It travels in three ways: Conduction (through solids), Convection (through fluids), and Radiation (through space/waves). Temperature measures the average kinetic energy of particles in a substance.",
                "Wave mechanics forms the foundation of understanding energy transfer in nature. Unlike particles, waves propagate through space by oscillating the medium's particles around their equilibrium positions. This means energy travels without permanent displacement of matter, a crucial distinction in physics.",
                "The mathematical description of waves involves parameters like wavelength, frequency, amplitude, and phase. The wave equation governs their behavior, showing how disturbances propagate at characteristic speeds determined by the medium's properties. Interference patterns demonstrate wave superposition, where multiple waves combine constructively or destructively.",
                "Applications of wave theory span from sound engineering to quantum mechanics. Electromagnetic waves require no medium and travel through vacuum, while mechanical waves like sound need material media. Understanding wave phenomena is essential for technologies including radio communication, medical imaging, seismology, and modern quantum computers."
            )
            Lang.HI -> listOf(
                "गति समय के साथ स्थिति में परिवर्तन है। दूरी और गति जैसे स्केलर बताते हैं कि कोई वस्तु कितनी दूर और तेज़ चलती है, जबकि विस्थापन और वेग जैसे वेक्टर में दिशा शामिल होती है। त्वरण मापता है कि वेग कितनी जल्दी बदलता है, जिससे वस्तु की गतिकी की पूरी तस्वीर मिलती है।",
                "बल धक्का या खिंचाव हैं जो गति बदलते हैं। न्यूटन के तीन नियम इसे परिभाषित करते हैं: 1) जड़त्व (वस्तुएं गति में परिवर्तन का विरोध करती हैं), 2) F=ma (बल द्रव्यमान गुणा त्वरण के बराबर होता है), और 3) क्रिया-प्रतिक्रिया (प्रत्येक बल का एक समान और विपरीत बल होता है)।",
                "कार्य तब होता है जब कोई बल किसी वस्तु को हिलाता है। ऊर्जा कार्य करने की क्षमता है, जो गतिज (गति) या स्थितिज (संग्रहीत) के रूप में मौजूद होती है। शक्ति कार्य करने की दर को मापती है। ऊर्जा संरक्षण का नियम कहता है कि ऊर्जा को न तो बनाया जा सकता है और न ही नष्ट किया जा सकता है।",
                "प्रकाश तरंग और कण दोनों के रूप में व्यवहार करता है। परावर्तन तब होता है जब प्रकाश दर्पण जैसी सतहों से टकराकर वापस आता है, उस नियम का पालन करते हुए जहां आपतन कोण परावर्तन कोण के बराबर होता है। यह सिद्धांत हमें वस्तुओं को देखने और चित्र बनाने की अनुमति देता है।",
                "ऊष्मा तापमान के अंतर के कारण तापीय ऊर्जा का स्थानांतरण है। यह तीन तरह से यात्रा करती है: चालन (ठोस के माध्यम से), संवहन (तरल पदार्थ के माध्यम से), और विकिरण (अंतरिक्ष/तरंगों के माध्यम से)। तापमान किसी पदार्थ में कणों की औसत गतिज ऊर्जा को मापता है।",
                "तरंग यांत्रिकी प्रकृति में ऊर्जा हस्तांतरण को समझने की नींव बनाती है। कणों के विपरीत, तरंगें माध्यम के कणों को उनकी संतुलन स्थिति के चारों ओर दोलन करके अंतरिक्ष के माध्यम से प्रसारित होती हैं। इसका मतलब है कि ऊर्जा पदार्थ के स्थायी विस्थापन के बिना यात्रा करती है।",
                "तरंगों का गणितीय विवरण तरंग दैर्घ्य, आवृत्ति, आयाम और चरण जैसे मापदंडों को शामिल करता है। तरंग समीकरण उनके व्यवहार को नियंत्रित करता है, यह दिखाता है कि माध्यम के गुणों द्वारा निर्धारित विशेषता गति पर गड़बड़ी कैसे फैलती है। व्यतिकरण पैटर्न तरंग अध्यारोपण को प्रदर्शित करता है।",
                "तरंग सिद्धांत के अनुप्रयोग ध्वनि इंजीनियरिंग से लेकर क्वांटम यांत्रिकी तक फैले हुए हैं। विद्युत चुम्बकीय तरंगों को किसी माध्यम की आवश्यकता नहीं होती है और निर्वात के माध्यम से यात्रा करती हैं, जबकि ध्वनि जैसी यांत्रिक तरंगों को भौतिक माध्यम की आवश्यकता होती है।"
            )
        }
    }
}
