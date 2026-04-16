package com.example.inscit.syllabus

import com.example.inscit.Branch
import com.example.inscit.models.TopicDetail
import com.example.inscit.models.Lang

object TopicSyllabus {
    fun getTopics(branch: Branch, lang: Lang): List<TopicDetail> {
        return when (branch) {
            Branch.PHYSICS -> getPhysicsTopics(lang)
            Branch.CHEMISTRY -> getChemistryTopics(lang)
            Branch.BIOLOGY -> getBiologyTopics(lang)
        }
    }

    private fun getPhysicsTopics(lang: Lang): List<TopicDetail> {
        return if (lang == Lang.EN) listOf(
            TopicDetail("p1", "Kinematics", listOf(
                "Kinematics describes the motion of objects without considering the forces that cause them to move. It focuses on displacement, velocity, and acceleration.",
                "In uniform motion, velocity is constant. In non-uniform motion, acceleration changes velocity over time, requiring precise mathematical models.",
                "Graphs like position-time and velocity-time are vital for visualizing these changes and calculating displacement geometrically."
            ), Branch.PHYSICS),
            TopicDetail("p2", "Newton's Laws", listOf(
                "Newton's First Law (Inertia) states objects resist changes in motion. The Second Law (F=ma) quantifies how force affects acceleration.",
                "The Third Law states every action has an equal and opposite reaction, meaning forces always exist in pairs between interacting bodies.",
                "These laws form the foundation of classical mechanics, explaining everything from daily movements to planetary orbits."
            ), Branch.PHYSICS),
            TopicDetail("p3", "Work and Energy", listOf(
                "Work is force applied over a distance. Energy is the capacity to do work, existing as Kinetic (motion) or Potential (stored).",
                "The Law of Conservation of Energy ensures energy is never created or destroyed, only transformed between different states.",
                "Power measures the rate of energy transfer, essential for understanding the efficiency of machines and natural processes."
            ), Branch.PHYSICS),
            TopicDetail("p4", "Light Reflection", listOf(
                "Reflection occurs when light bounces off a surface. The Law of Reflection states the angle of incidence equals the angle of reflection.",
                "Mirrors use reflection to form images. Real images can be projected on a screen, while virtual images appear behind the surface.",
                "The predictable behavior of light rays allows for the design of optical devices like periscopes, telescopes, and microscopes."
            ), Branch.PHYSICS),
            TopicDetail("p5", "Heat Transfer", listOf(
                "Heat moves via Conduction (solid collisions), Convection (fluid movement), or Radiation (electromagnetic waves).",
                "Temperature measures the average kinetic energy of particles. Heat naturally flows from warmer bodies to cooler ones until equilibrium.",
                "Understanding thermal energy transfer is crucial for engineering cooling systems, building insulation, and climate studies."
            ), Branch.PHYSICS),
            TopicDetail("p6", "Wave Mechanics", listOf(
                "Waves transfer energy through oscillations without moving matter permanently. Key properties include frequency, wavelength, and amplitude.",
                "Superposition and interference occur when waves overlap, creating patterns of constructive or destructive reinforcement.",
                "Wave theory explains sound propagation, water waves, and the fundamental behavior of light and quantum particles."
            ), Branch.PHYSICS),
            TopicDetail("p7", "Electromagnetism", listOf(
                "Electromagnetism studies the interaction between electric charges and magnetic fields, forming one of nature's four fundamental forces.",
                "Electric currents generate magnetic fields, and changing magnetic fields can induce electric currents, a principle used in generators.",
                "Maxwell's equations unify electricity and magnetism into a single framework, predicting the existence of electromagnetic waves."
            ), Branch.PHYSICS),
            TopicDetail("p8", "Nuclear Physics", listOf(
                "Nuclear physics explores the constituents and interactions of atomic nuclei, governed by the strong and weak nuclear forces.",
                "Radioactivity, fission, and fusion are processes that release vast amounts of energy from the binding forces within an atom.",
                "Applications range from generating carbon-free electricity in nuclear plants to advanced medical diagnostics and treatments."
            ), Branch.PHYSICS)
        ) else listOf(
            TopicDetail("p1", "गतिकी (Kinematics)", listOf(
                "गतिकी बलों पर विचार किए बिना वस्तुओं की गति का वर्णन करती है। यह विस्थापन, वेग और त्वरण पर केंद्रित है।",
                "समान गति में, वेग स्थिर रहता है। गैर-समान गति में, त्वरण समय के साथ वेग बदलता है, जिसके लिए सटीक मॉडल की आवश्यकता होती है।",
                "स्थिति-समय और वेग-समय ग्राफ इन परिवर्तनों को देखने और विस्थापन की गणना करने के लिए महत्वपूर्ण हैं।"
            ), Branch.PHYSICS),
            TopicDetail("p2", "न्यूटन के नियम", listOf(
                "न्यूटन का पहला नियम (जड़त्व) कहता है कि वस्तुएं गति में परिवर्तन का विरोध करती हैं। दूसरा नियम (F=ma) बल और त्वरण को जोड़ता है.",
                "तीसरा नियम कहता है कि प्रत्येक क्रिया की एक समान और विपरीत प्रतिक्रिया होती है, जिसका अर्थ है कि बल हमेशा जोड़ों में मौजूद होते हैं।",
                "ये नियम शास्त्रीय यांत्रिकी की नींव बनाते हैं, जो दैनिक आंदोलनों से लेकर ग्रहों की कक्षाओं तक सब कुछ समझाते हैं।"
            ), Branch.PHYSICS),
            TopicDetail("p3", "कार्य और ऊर्जा", listOf(
                "कार्य एक दूरी पर लगाया गया बल है। ऊर्जा कार्य करने की क्षमता है, जो गतिज (गति) या स्थितिज (संग्रहीत) के रूप में होती है।",
                "ऊर्जा संरक्षण का नियम सुनिश्चित करता है कि ऊर्जा कभी बनाई या नष्ट नहीं होती, केवल विभिन्न अवस्थाओं के बीच बदलती है।",
                "शक्ति ऊर्जा हस्तांतरण की दर को मापती है, जो मशीनों और प्राकृतिक प्रक्रियाओं की दक्षता को समझने के लिए आवश्यक है।"
            ), Branch.PHYSICS),
            TopicDetail("p4", "प्रकाश परावर्तन", listOf(
                "परावर्तन तब होता है जब प्रकाश किसी सतह से टकराकर वापस आता है। परावर्तन का नियम कहता है कि आपतन कोण परावर्तन कोण के बराबर होता है।",
                "दर्पण चित्र बनाने के लिए परावर्तन का उपयोग करते हैं। वास्तविक चित्र स्क्रीन पर देखे जा सकते हैं, जबकि आभासी चित्र सतह के पीछे दिखाई देते हैं।",
                "प्रकाश किरणों का व्यवहार पेरिस्कोप, टेलीस्कोप और माइक्रोस्कोप जैसे ऑप्टिकल उपकरणों के डिजाइन की अनुमति देता है।"
            ), Branch.PHYSICS),
            TopicDetail("p5", "ऊष्मा स्थानांतरण", listOf(
                "ऊष्मा चालन (ठोस), संवहन (तरल/गैस), या विकिरण (तरंगों) के माध्यम से चलती है।",
                "तापमान कणों की औसत गतिज ऊर्जा को मापता है। ऊष्मा स्वाभाविक रूप से गर्म निकायों से ठंडे निकायों की ओर बहती है।",
                "तापीय ऊर्जा हस्तांतरण को समझना कूलिंग सिस्टम और निर्माण इन्सुलेशन इंजीनियरिंग के लिए महत्वपूर्ण है।"
            ), Branch.PHYSICS),
            TopicDetail("p6", "तरंग यांत्रिकी", listOf(
                "तरंगें पदार्थ को स्थायी रूप से हिलाए बिना दोलनों के माध्यम से ऊर्जा स्थानांतरित करती हैं। मुख्य गुणों में आवृत्ति और तरंग दैर्घ्य शामिल हैं।",
                "अध्यारोपण और व्यतिकरण तब होते हैं जब तरंगें एक-दूसरे पर पड़ती हैं, जिससे रचनात्मक या विनाशकारी पैटर्न बनते हैं।",
                "तरंग सिद्धांत ध्वनि प्रसार, पानी की तरंगों और प्रकाश के व्यवहार की व्याख्या करता है।"
            ), Branch.PHYSICS),
            TopicDetail("p7", "विद्युत चुंबकत्व", listOf(
                "विद्युत चुंबकत्व विद्युत आवेशों और चुंबकीय क्षेत्रों के बीच परस्पर क्रिया का अध्ययन करता है।",
                "विद्युत धाराएं चुंबकीय क्षेत्र उत्पन्न करती हैं, और बदलते चुंबकीय क्षेत्र विद्युत धाराएं प्रेरित कर सकते हैं।",
                "मैक्सवेल के समीकरणों ने बिजली और चुंबकत्व को एक ढांचे में एकीकृत किया, जिससे विद्युत चुम्बकीय तरंगों की भविष्यवाणी हुई।"
            ), Branch.PHYSICS),
            TopicDetail("p8", "परमाणु भौतिकी", listOf(
                "परमाणु भौतिकी परमाणु नाभिक के घटकों और अंतःक्रियाओं का पता लगाती है।",
                "रेडियोधर्मिता, विखंडन और संलयन ऐसी प्रक्रियाएं हैं जो परमाणु के भीतर से भारी मात्रा में ऊर्जा मुक्त करती हैं।",
                "इसके अनुप्रयोग परमाणु संयंत्रों में बिजली बनाने से लेकर उन्नत चिकित्सा निदान और उपचार तक फैले हुए हैं।"
            ), Branch.PHYSICS)
        )
    }

    private fun getChemistryTopics(lang: Lang): List<TopicDetail> {
        return if (lang == Lang.EN) listOf(
            TopicDetail("c1", "States of Matter", listOf(
                "Matter exists primarily as solid, liquid, and gas. Changes occur through heating or cooling, leading to phase transitions like melting or boiling.",
                "Molecular kinetic theory explains state behavior based on particle motion and energy levels within the substance.",
                "Extreme states like Plasma and Bose-Einstein condensates exist under specific conditions of high energy or near absolute zero."
            ), Branch.CHEMISTRY),
            TopicDetail("c2", "Elements & Mixtures", listOf(
                "Pure substances are elements or compounds. Mixtures are physical combinations of substances that can be separated by physical means.",
                "Mixtures are homogeneous (uniform) or heterogeneous (distinct parts). Separation techniques include filtration and distillation.",
                "Elements are the simplest chemical substances, each defined by its atomic number and unique chemical properties."
            ), Branch.CHEMISTRY),
            TopicDetail("c3", "Atomic Structure", listOf(
                "Atoms consist of protons, neutrons, and electrons. The nucleus contains protons and neutrons, while electrons occupy shells around it.",
                "Bohr's model describes electrons in fixed orbits, while modern quantum theory views them as probability clouds called orbitals.",
                "Atomic identity is determined by the number of protons (atomic number), while mass depends on both protons and neutrons."
            ), Branch.CHEMISTRY),
            TopicDetail("c4", "Chemical Reactions", listOf(
                "Chemical reactions transform reactants into products through the breaking and forming of chemical bonds.",
                "The Law of Conservation of Mass dictates that mass remains constant, requiring balanced chemical equations to represent reactions accurately.",
                "Exothermic reactions release energy, while endothermic ones absorb it. Catalysts can influence the rate without being consumed."
            ), Branch.CHEMISTRY),
            TopicDetail("c5", "Acids and Bases", listOf(
                "Acids donate H+ ions and have low pH, while bases accept H+ or produce OH- ions and have high pH.",
                "Indicators like litmus paper help identify substances on the pH scale, which measures acidity or alkalinity from 0 to 14.",
                "Neutralization reactions between acids and bases produce salt and water, a fundamental concept in both biology and industry."
            ), Branch.CHEMISTRY),
            TopicDetail("c6", "Quantum Theory", listOf(
                "Quantum theory uses wave functions and orbitals to describe electron positions as regions of high probability.",
                "Four quantum numbers (n, l, m, s) uniquely define each electron's state, explaining the periodic table's arrangement.",
                "This model accounts for atomic spectra and the complex electronic behavior that governs chemical reactivity."
            ), Branch.CHEMISTRY),
            TopicDetail("c7", "Periodic Trends", listOf(
                "The Periodic Table organizes elements by properties. Trends like electronegativity and atomic radius repeat across periods.",
                "Ionization energy and electron affinity determine how elements react, based on their position in groups and periods.",
                "Modern periodic law states that properties of elements are periodic functions of their atomic numbers."
            ), Branch.CHEMISTRY),
            TopicDetail("c8", "Chemical Bonding", listOf(
                "Chemical bonds hold atoms together. Ionic bonds involve electron transfer, while covalent bonds involve electron sharing.",
                "Metallic bonding features a 'sea of electrons', giving metals their unique conductivity and structural properties.",
                "VSEPR theory explains molecular shapes, which are critical for understanding how molecules interact in biological systems."
            ), Branch.CHEMISTRY)
        ) else listOf(
            TopicDetail("c1", "पदार्थ की अवस्थाएं", listOf(
                "पदार्थ ठोस, तरल और गैस के रूप में मौजूद होता है। ऊर्जा के परिवर्तन से पिघलने या उबलने जैसी अवस्थाएं बदलती हैं।",
                "आणविक गतिज सिद्धांत कणों की गति और ऊर्जा के आधार पर इन अवस्थाओं की व्याख्या करता है।",
                "प्लाज्मा और बोस-आइंस्टीन कंडेनसेट विशिष्ट परिस्थितियों में मौजूद चरम अवस्थाएं हैं।"
            ), Branch.CHEMISTRY),
            TopicDetail("c2", "तत्व और मिश्रण", listOf(
                "शुद्ध पदार्थ तत्व या यौगिक होते हैं। मिश्रण पदार्थों का भौतिक संयोजन होते हैं जिन्हें अलग किया जा सकता है।",
                "मिश्रण सजातीय या विजातीय होते हैं। पृथक्करण तकनीकों में निस्पंदन और आसवन शामिल हैं।",
                "तत्व सबसे सरल रासायनिक पदार्थ हैं, जिनमें से प्रत्येक की अपनी परमाणु संख्या और गुण होते हैं।"
            ), Branch.CHEMISTRY),
            TopicDetail("c3", "परमाणु संरचना", listOf(
                "परमाणु में प्रोटॉन, न्यूट्रॉन और इलेक्ट्रॉन होते हैं। नाभिक में प्रोटॉन और न्यूट्रॉन होते हैं।",
                "बोहर का मॉडल इलेक्ट्रॉनों को निश्चित कक्षाओं में वर्णित करता है, जबकि आधुनिक सिद्धांत उन्हें कक्षक (orbitals) के रूप में देखता है।",
                "परमाणु की पहचान प्रोटॉन की संख्या से निर्धारित होती है, जबकि द्रव्यमान प्रोटॉन और न्यूट्रॉन दोनों पर निर्भर करता है।"
            ), Branch.CHEMISTRY),
            TopicDetail("c4", "रासायनिक अभिक्रियाएं", listOf(
                "रासायनिक अभिक्रियाएं बंधों के टूटने और बनने के माध्यम से अभिकारकों को उत्पादों में बदलती हैं।",
                "द्रव्यमान संरक्षण का नियम कहता है कि द्रव्यमान स्थिर रहता है, इसलिए संतुलित समीकरण आवश्यक हैं।",
                "ऊष्माक्षेपी अभिक्रियाएं ऊर्जा छोड़ती हैं, जबकि ऊष्माशोषी अवशोषित करती हैं। उत्प्रेरक दर को प्रभावित कर सकते हैं।"
            ), Branch.CHEMISTRY),
            TopicDetail("c5", "अम्ल और क्षार", listOf(
                "अम्ल H+ आयन देते हैं और pH कम होता है, जबकि क्षार H+ स्वीकार करते हैं और pH अधिक होता है।",
                "लिटमस जैसे संकेतक pH स्केल पर पदार्थों की पहचान करने में मदद करते हैं, जो 0 से 14 तक होता है।",
                "अम्ल और क्षार के बीच उदासीनीकरण से लवण और पानी बनता है, जो एक मौलिक अवधारणा है।"
            ), Branch.CHEMISTRY),
            TopicDetail("c6", "क्वांटम सिद्धांत", listOf(
                "क्वांटम सिद्धांत इलेक्ट्रॉनों की स्थिति को उच्च प्रायिकता वाले क्षेत्रों (कक्षक) के रूप में वर्णित करता है।",
                "चार क्वांटम संख्याएं प्रत्येक इलेक्ट्रॉन की स्थिति को परिभाषित करती हैं, जो आवर्त सारणी की व्याख्या करती हैं।",
                "यह मॉडल परमाणु स्पेक्ट्रा और जटिल इलेक्ट्रॉनिक व्यवहार के लिए उत्तरदायी है।"
            ), Branch.CHEMISTRY),
            TopicDetail("c7", "आवर्त प्रवृत्तियाँ", listOf(
                "आवर्त सारणी तत्वों को गुणों के आधार पर व्यवस्थित करती है। त्रिज्या और विद्युत ऋणात्मकता जैसी प्रवृत्तियाँ दोहराई जाती हैं।",
                "आयनन ऊर्जा और इलेक्ट्रॉन बंधुता यह निर्धारित करती है कि तत्व अपनी स्थिति के आधार पर कैसे प्रतिक्रिया करते हैं।",
                "तत्वों के गुण उनकी परमाणु संख्याओं के आवर्ती कार्य (periodic functions) होते हैं।"
            ), Branch.CHEMISTRY),
            TopicDetail("c8", "रासायनिक बंधन", listOf(
                "रासायनिक बंधन परमाणुओं को जोड़ते हैं। आयनिक में इलेक्ट्रॉन का स्थानांतरण और सहसंयोजक में साझाकरण होता है।",
                "धात्विक बंधन में 'इलेक्ट्रॉनों का समुद्र' होता है, जो धातुओं को उनकी चालकता प्रदान करता है।",
                "VSEPR सिद्धांत आणविक आकृतियों की व्याख्या करता है, जो जैविक प्रणालियों में महत्वपूर्ण हैं।"
            ), Branch.CHEMISTRY)
        )
    }

    private fun getBiologyTopics(lang: Lang): List<TopicDetail> {
        return if (lang == Lang.EN) listOf(
            TopicDetail("b1", "Cell Structure", listOf(
                "Cells are life's basic units. Eukaryotes have a nucleus and organelles like mitochondria for energy.",
                "The cell membrane controls entry/exit, while the cytoskeleton provides structural support.",
                "Plant cells have cell walls and chloroplasts for photosynthesis, distinguishing them from animal cells."
            ), Branch.BIOLOGY),
            TopicDetail("b2", "Plant Tissues", listOf(
                "Plant tissues are for growth and transport. Meristems allow growth; xylem and phloem transport resources.",
                "Xylem moves water upward; phloem moves sugars from leaves to the rest of the plant.",
                "Dermal and ground tissues protect and support the plant's overall structure and health."
            ), Branch.BIOLOGY),
            TopicDetail("b3", "Metabolism", listOf(
                "Nutrition provides energy. Plants use photosynthesis (autotrophs); animals eat (heterotrophs).",
                "Metabolism includes catabolism (breaking down) and anabolism (building up) molecules in cells.",
                "Enzymes are biological catalysts that speed up these vital chemical reactions inside organisms."
            ), Branch.BIOLOGY),
            TopicDetail("b4", "Respiration", listOf(
                "Cellular respiration in mitochondria converts glucose into ATP, providing energy for life processes.",
                "The circulatory system transports oxygen and nutrients to cells while removing waste like CO2.",
                "Gas exchange occurs in specialized organs like lungs in humans or gills in aquatic animals."
            ), Branch.BIOLOGY),
            TopicDetail("b5", "DNA & Heredity", listOf(
                "DNA carries genetic information. Reproduction ensures traits are passed to the next generation.",
                "Mitosis is for growth and repair; meiosis produces gametes for sexual reproduction and diversity.",
                "Genes are segments of DNA that determine specific characteristics of an organism."
            ), Branch.BIOLOGY),
            TopicDetail("b6", "Endocrine System", listOf(
                "Glands produce hormones that regulate growth, metabolism, and mood as chemical messengers.",
                "The pituitary gland coordinates other glands, while the pancreas regulates blood sugar levels.",
                "Hormonal balance is critical for maintaining the body's stable internal environment (homeostasis)."
            ), Branch.BIOLOGY),
            TopicDetail("b7", "Nervous System", listOf(
                "The nervous system coordinates body actions via electrical signals and neurons.",
                "The brain and spinal cord form the central system, while peripheral nerves reach every limb.",
                "Synapses allow neurons to communicate via neurotransmitters, enabling sensation and thought."
            ), Branch.BIOLOGY),
            TopicDetail("b8", "Ecology", listOf(
                "Ecology studies how organisms interact with each other and their physical environment.",
                "Ecosystems consist of producers, consumers, and decomposers in a complex web of life.",
                "Energy flows through food chains, while matter like carbon and nitrogen is recycled globally."
            ), Branch.BIOLOGY)
        ) else listOf(
            TopicDetail("b1", "कोशिका संरचना", listOf(
                "कोशिकाएं जीवन की मूल इकाइयां हैं। यूकैरियोट्स में नाभिक और ऊर्जा के लिए माइटोकॉन्ड्रिया होते हैं।",
                "कोशिका झिल्ली प्रवेश/निकास को नियंत्रित करती है, जबकि साइटोस्केलेटन संरचनात्मक सहायता प्रदान करता है।",
                "पादप कोशिकाओं में कोशिका भित्ति और क्लोरोप्लास्ट होते हैं, जो उन्हें पशु कोशिकाओं से अलग करते हैं।"
            ), Branch.BIOLOGY),
            TopicDetail("b2", "पादप ऊतक", listOf(
                "पादप ऊतक वृद्धि और परिवहन के लिए हैं। विभज्योतक विकास की अनुमति देते हैं; जाइलम और फ्लोएम परिवहन करते हैं।",
                "जाइलम पानी को ऊपर ले जाता है; फ्लोएम पत्तियों से शर्करा को पौधे के बाकी हिस्सों में ले जाता है।",
                "त्वचीय और आधार ऊतक पौधे की समग्र संरचना और स्वास्थ्य की रक्षा और सहायता करते हैं।"
            ), Branch.BIOLOGY),
            TopicDetail("b3", "चयापचय", listOf(
                "पोषण ऊर्जा प्रदान करता है। पौधे प्रकाश संश्लेषण (स्वपोषी) का उपयोग करते हैं; पशु भोजन करते हैं (परपोषी)।",
                "चयापचय में कोशिकाओं में अणुओं का अपचय (तोड़ना) और उपचय (बनाना) शामिल है।",
                "एंजाइम जैविक उत्प्रेरक हैं जो जीवों के अंदर इन महत्वपूर्ण रासायनिक अभिक्रियाओं को तेज करते हैं।"
            ), Branch.BIOLOGY),
            TopicDetail("b4", "श्वसन", listOf(
                "मािटोकॉन्ड्रिया में कोशकीय श्वसन ग्लूकोज को ATP में बदल देता है, जो जीवन के लिए ऊर्जा प्रदान करता है।",
                "संचार प्रणाली कोशिकाओं को ऑक्सीजन और पोषक तत्व पहुंचाती है और CO2 जैसे कचरे को हटाती है।",
                "गैस विनिमय विशिष्ट अंगों जैसे मनुष्यों में फेफड़े या जलीय जंतुओं में गलफड़ों में होता है।"
            ), Branch.BIOLOGY),
            TopicDetail("b5", "DNA और आनुवंशिकता", listOf(
                "DNA आनुवंशिक जानकारी ले जाता है। प्रजनन सुनिश्चित करता है कि लक्षण अगली पीढ़ी तक पहुँचें।",
                "समसूत्री विभाजन विकास के लिए है; अर्धसूत्रीविभाजन प्रजनन और विविधता के लिए युग्मक उत्पन्न करता है।",
                "जीन DNA के खंड हैं जो किसी जीव की विशिष्ट विशेषताओं को निर्धारित करते हैं।"
            ), Branch.BIOLOGY),
            TopicDetail("b6", "अंतःस्रावी तंत्र", listOf(
                "ग्रंथियां हार्मोन का उत्पादन करती हैं जो रासायनिक संदेशवाहक के रूप में विकास और चयापचय को नियंत्रित करते हैं।",
                "पीयूषिका ग्रंथि अन्य ग्रंथियों का समन्वय करती है, जबकि अग्न्याशय रक्त शर्करा के स्तर को नियंत्रित करता है।",
                "हार्मोनल संतुलन शरीर के स्थिर आंतरिक वातावरण (होमियोस्टैसिस) को बनाए रखने के लिए महत्वपूर्ण है।"
            ), Branch.BIOLOGY),
            TopicDetail("b7", "तंत्रिका तंत्र", listOf(
                "तंत्रिका तंत्र विद्युत संकेतों और न्यूरॉन्स के माध्यम से शरीर के कार्यों का समन्वय करता है।",
                "मस्तिष्क और रीढ़ की हड्डी केंद्रीय प्रणाली बनाते हैं, जबकि परिधीय तंत्रिकाएं हर अंग तक पहुँचती हैं।",
                "सिनैप्स न्यूरॉन्स को न्यूरोट्रांसमीटर के माध्यम से संचार करने की अनुमति देते हैं, जिससे संवेदना संभव होती है।"
            ), Branch.BIOLOGY),
            TopicDetail("b8", "पारिस्थितिकी", listOf(
                "पारिस्थितिकी अध्ययन करती है कि जीव एक-दूसरे और अपने पर्यावरण के साथ कैसे अंतःक्रिया करते हैं।",
                "पारिस्थितिकी तंत्र में जीवन के एक जटिल जाल में उत्पादक, उपभोक्ता और अपघटक होते हैं।",
                "ऊर्जा खाद्य श्रृंखलाओं के माध्यम से बहती है, जबकि कार्बन और नाइट्रोजन जैसे पदार्थ पुनर्चक्रित होते हैं।"
            ), Branch.BIOLOGY)
        )
    }
}
