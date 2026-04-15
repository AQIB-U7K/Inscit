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
            TopicDetail(
                "p1", "Kinematics",
                listOf(
                    "Kinematics is the branch of classical mechanics that describes the motion of points, bodies (objects), and systems of bodies (groups of objects) without considering the forces that cause them to move. It focuses on parameters such as displacement, velocity, and acceleration over time.",
                    "In uniform motion, an object covers equal distances in equal intervals of time, resulting in a constant velocity. However, most real-world motions are non-uniform, where acceleration plays a critical role in changing the speed or direction of the moving body, requiring calculus for precise description.",
                    "Graphs are essential tools in kinematics for visualizing motion. A position-time graph's slope represents velocity, while a velocity-time graph's slope represents acceleration. The area under a velocity-time graph provides the total displacement, allowing for a geometric interpretation of physical movement."
                ),
                Branch.PHYSICS
            ),
            TopicDetail(
                "p2", "Newton's Laws",
                listOf(
                    "Newton's First Law, also known as the Law of Inertia, states that an object at rest stays at rest, and an object in motion stays in motion with the same speed and in the same direction unless acted upon by an unbalanced force. Inertia is the inherent property of matter to resist changes in its state of motion.",
                    "The Second Law provides a quantitative description of force: the acceleration of an object is directly proportional to the net force acting on it and inversely proportional to its mass (F = ma). This relationship defines how the velocity of an object changes when it is subjected to external forces.",
                    "Newton's Third Law states that for every action, there is an equal and opposite reaction. This means that forces always exist in pairs; when one body exerts a force on a second body, the second body simultaneously exerts a force of equal magnitude and opposite direction on the first body."
                ),
                Branch.PHYSICS
            ),
            TopicDetail(
                "p3", "Work and Energy",
                listOf(
                    "Work is defined as the product of the component of the force in the direction of the displacement and the magnitude of this displacement. In physics, work is only done when a force causes an object to move, and it is measured in Joules (J).",
                    "Energy is the capacity to do work and exists in various forms, most notably Kinetic Energy (energy of motion) and Potential Energy (stored energy). The Work-Energy Theorem states that the net work done on an object is equal to the change in its kinetic energy.",
                    "Power is the rate at which work is performed or energy is transferred. It is calculated by dividing the work done by the time interval taken to do it. The Law of Conservation of Energy ensures that energy is never lost but only transformed from one state to another within a closed system."
                ),
                Branch.PHYSICS
            ),
            TopicDetail(
                "p4", "Light Reflection",
                listOf(
                    "Reflection is the change in direction of a wavefront at an interface between two different media so that the wavefront returns into the medium from which it originated. Light reflection allows us to perceive our surroundings by bouncing off non-luminous objects.",
                    "The Law of Reflection states that the angle of incidence is equal to the angle of reflection, and the incident ray, reflected ray, and the normal to the surface all lie in the same plane. This predictable behavior is the basis for image formation in mirrors.",
                    "Images formed by reflection can be real or virtual. Real images can be projected onto a screen because light rays actually meet at a point, whereas virtual images appear to originate from a point behind the mirror and cannot be captured on a physical surface."
                ),
                Branch.PHYSICS
            ),
            TopicDetail(
                "p5", "Heat Transfer",
                listOf(
                    "Conduction is the process by which heat energy is transmitted through collisions between neighboring atoms or molecules. It occurs most effectively in solids, particularly metals, where free electrons help transport thermal energy rapidly across the material.",
                    "Convection is the transfer of heat by the physical movement of a fluid (liquid or gas). As a fluid is heated, it becomes less dense and rises, while cooler, denser fluid sinks, creating convection currents that distribute thermal energy throughout the medium.",
                    "Radiation is the transfer of energy through electromagnetic waves, such as infrared light. Unlike conduction and convection, radiation does not require a material medium to travel, allowing heat from the Sun to reach the Earth through the vacuum of space."
                ),
                Branch.PHYSICS
            )
        ) else listOf(
             TopicDetail(
                "p1", "गतिकी (Kinematics)",
                listOf(
                    "गतिकी शास्त्रीय यांत्रिकी की वह शाखा है जो बिंदुओं, निकायों (वस्तुओं) और निकायों के प्रणालियों की गति का वर्णन करती है, बिना उन बलों पर विचार किए जो उन्हें गति करने का कारण बनते हैं। यह विस्थापन, वेग और समय के साथ त्वरण जैसे मापदंडों पर ध्यान केंद्रित करता है।",
                    "समान गति में, एक वस्तु समय के समान अंतराल में समान दूरी तय करती है, जिसके परिणामस्वरूप वेग स्थिर रहता है। हालांकि, अधिकांश वास्तविक दुनिया की गतियां गैर-समान होती हैं, जहां त्वरण गतिमान शरीर की गति या दिशा बदलने में महत्वपूर्ण भूमिका निभाता है।",
                    "गतिकी में गति की कल्पना करने के लिए ग्राफ आवश्यक उपकरण हैं। स्थिति-समय ग्राफ का ढाल वेग का प्रतिनिधित्व करता है, जबकि वेग-समय ग्राफ का ढाल त्वरण का प्रतिनिधित्व करता है। वेग-समय ग्राफ के नीचे का क्षेत्र कुल विस्थापन प्रदान करता है।"
                ),
                Branch.PHYSICS
            ),
            TopicDetail(
                "p2", "न्यूटन के नियम",
                listOf(
                    "न्यूटन का पहला नियम, जिसे जड़त्व के नियम के रूप में भी जाना जाता है, कहता है कि एक स्थिर वस्तु स्थिर रहती है, और गतिमान वस्तु समान गति और समान दिशा में गति करती रहती है जब तक कि उस पर कोई बाहरी बल कार्य न करे। जड़त्व पदार्थ का अंतर्निहित गुण है।",
                    "दूसरा नियम बल का मात्रात्मक विवरण प्रदान करता है: किसी वस्तु का त्वरण उस पर कार्य करने वाले शुद्ध बल के सीधे आनुपातिक होता है और उसके द्रव्यमान (F = ma) के व्युत्क्रमानुपाती होता है। यह संबंध बताता है कि बाहरी बलों के अधीन होने पर वस्तु का वेग कैसे बदलता है।",
                    "न्यूटन का तीसरा नियम कहता है कि प्रत्येक क्रिया की एक समान और विपरीत प्रतिक्रिया होती है। इसका मतलब है कि बल हमेशा जोड़ों में मौजूद होते हैं; जब एक शरीर दूसरे शरीर पर बल लगाता है, तो दूसरा शरीर एक साथ पहले शरीर पर समान परिमाण और विपरीत दिशा में बल लगाता है।"
                ),
                Branch.PHYSICS
            ),
            TopicDetail(
                "p3", "कार्य और ऊर्जा",
                listOf(
                    "कार्य को विस्थापन की दिशा में बल के घटक और इस विस्थापन के परिमाण के गुणनफल के रूप में परिभाषित किया गया है। भौतिकी में, कार्य केवल तब होता है जब एक बल किसी वस्तु को हिलाता है, और इसे जूल (J) में मापा जाता है।",
                    "ऊर्जा कार्य करने की क्षमता है और विभिन्न रूपों में मौजूद है, विशेष रूप से गतिज ऊर्जा (गति की ऊर्जा) और स्थितिज ऊर्जा (संग्रहीत ऊर्जा)। कार्य-ऊर्जा प्रमेय कहता है कि किसी वस्तु पर किया गया शुद्ध कार्य उसकी गतिज ऊर्जा में परिवर्तन के बराबर होता है।",
                    "शक्ति वह दर है जिस पर कार्य किया जाता है या ऊर्जा स्थानांतरित की जाती है। इसकी गणना किए गए कार्य को उसे करने में लगे समय अंतराल से विभाजित करके की जाती है। ऊर्जा संरक्षण का नियम सुनिश्चित करता है कि ऊर्जा कभी नष्ट नहीं होती।"
                ),
                Branch.PHYSICS
            ),
            TopicDetail(
                "p4", "प्रकाश परावर्तन",
                listOf(
                    "परावर्तन दो अलग-अलग माध्यमों के बीच एक इंटरफ़ेस पर तरंग के सामने की दिशा में परिवर्तन है ताकि तरंग उस माध्यम में वापस आ जाए जिससे वह उत्पन्न हुई थी। प्रकाश परावर्तन हमें वस्तुओं से टकराकर वापस आने के कारण अपने परिवेश को देखने की अनुमति देता है।",
                    "परावर्तन का नियम कहता है कि आपतन कोण परावर्तन कोण के बराबर होता है, और आपतित किरण, परावर्तित किरण और सतह पर लंब सभी एक ही तल में स्थित होते हैं। यह व्यवहार दर्पणों में चित्र बनने का आधार है।",
                    "परावर्तन द्वारा बनाए गए चित्र वास्तविक या आभासी हो सकते हैं। वास्तविक चित्रों को स्क्रीन पर प्रक्षेपित किया जा सकता है क्योंकि प्रकाश किरणें वास्तव में एक बिंदु पर मिलती हैं, जबकि आभासी चित्र दर्पण के पीछे एक बिंदु से उत्पन्न होते प्रतीत होते हैं।"
                ),
                Branch.PHYSICS
            ),
            TopicDetail(
                "p5", "ऊष्मा स्थानांतरण",
                listOf(
                    "चालन वह प्रक्रिया है जिसके द्वारा पड़ोसी परमाणुओं या अणुओं के बीच टकराव के माध्यम से ऊष्मा ऊर्जा प्रसारित होती है। यह ठोस पदार्थों, विशेष रूप से धातुओं में सबसे प्रभावी ढंग से होता है, जहां मुक्त इलेक्ट्रॉन तापीय ऊर्जा के परिवहन में मदद करते हैं।",
                    "संवहन एक तरल (तरल या गैस) की भौतिक गति द्वारा ऊष्मा का स्थानांतरण है। जैसे ही एक तरल गर्म होता है, यह कम घना हो जाता है और ऊपर उठता है, जबकि ठंडा, सघन तरल डूब जाता है, जिससे संवहन धाराएं बनती हैं।",
                    "विकिरण विद्युत चुम्बकीय तरंगों, जैसे अवरक्त प्रकाश, के माध्यम से ऊर्जा का स्थानांतरण है। चालन और संवहन के विपरीत, विकिरण को यात्रा करने के लिए किसी भौतिक माध्यम की आवश्यकता नहीं होती है, जिससे सूर्य की गर्मी पृथ्वी तक पहुँचती है।"
                ),
                Branch.PHYSICS
            )
        )
    }

    private fun getChemistryTopics(lang: Lang): List<TopicDetail> {
        return if (lang == Lang.EN) listOf(
            TopicDetail(
                "c1", "States of Matter",
                listOf(
                    "Matter exists primarily in three states: solid, liquid, and gas. Solids have a definite shape and volume because their particles are tightly packed and only vibrate in fixed positions. Liquids have a definite volume but take the shape of their container as particles can slide past each other.",
                    "Gases have neither a definite shape nor a definite volume, expanding to fill whatever space is available. Beyond these three, scientists also study plasma, a high-energy state where electrons are stripped from atoms, and Bose-Einstein condensates, which occur at near absolute zero temperatures.",
                    "Phase transitions occur when energy is added or removed from a substance, causing it to change from one state to another. Common transitions include melting, freezing, vaporization, condensation, and sublimation, where a solid turns directly into a gas without becoming a liquid."
                ),
                Branch.CHEMISTRY
            ),
            TopicDetail(
                "c2", "Elements & Mixtures",
                listOf(
                    "Pure substances consist of only one type of particle and include elements and compounds. Elements are the simplest form of matter that cannot be broken down by chemical means, while compounds are substances formed from two or more elements chemically united in fixed proportions.",
                    "Mixtures consist of two or more substances physically combined and can be categorized as homogeneous or heterogeneous. Homogeneous mixtures have a uniform composition throughout, like salt water, while heterogeneous mixtures, like a salad, have visibly different components.",
                    "Separating mixtures involves physical processes that exploit differences in properties such as boiling point, solubility, or particle size. Common techniques include filtration, distillation, chromatography, and evaporation, allowing for the recovery of individual components without chemical changes."
                ),
                Branch.CHEMISTRY
            ),
            TopicDetail(
                "c3", "Atomic Structure",
                listOf(
                    "The atom is the basic unit of a chemical element, consisting of a central nucleus surrounded by a cloud of electrons. Throughout history, models by Dalton, Thomson, Rutherford, and Bohr have refined our understanding of how these subatomic particles are arranged.",
                    "The nucleus contains protons, which carry a positive charge, and neutrons, which are electrically neutral. The number of protons defines the identity of the element (atomic number), while the sum of protons and neutrons determines its mass number.",
                    "Electrons are negatively charged particles that orbit the nucleus in specific energy levels or shells. The arrangement of these electrons, especially in the outermost shell, determines the chemical reactivity and bonding behavior of the atom with other elements."
                ),
                Branch.CHEMISTRY
            ),
            TopicDetail(
                "c4", "Chemical Reactions",
                listOf(
                    "A chemical reaction is a process that leads to the chemical transformation of one set of chemical substances to another. Reactants are the starting materials that undergo change, while products are the new substances formed as a result of the reaction.",
                    "The Law of Conservation of Mass states that mass is neither created nor destroyed in a chemical reaction. This principle requires chemical equations to be balanced, ensuring that the number of atoms of each element is the same on both the reactant and product sides.",
                    "Reactions can be classified by energy changes: exothermic reactions release energy into the surroundings, often as heat or light, while endothermic reactions absorb energy. Understanding these energetics is crucial for industrial chemistry and biological processes."
                ),
                Branch.CHEMISTRY
            ),
            TopicDetail(
                "c5", "Acids and Bases",
                listOf(
                    "Acids and bases are two classes of chemical compounds that display opposite properties. Acids are generally sour and can donate a hydrogen ion (H+), while bases are often bitter, feel slippery, and can accept a hydrogen ion or produce hydroxide ions (OH-).",
                    "The pH scale, ranging from 0 to 14, measures how acidic or basic a substance is. A pH of 7 is neutral, while values below 7 are acidic and values above 7 are basic. Logarithmic in nature, each whole pH value represents a tenfold change in acidity.",
                    "When an acid and a base react, they undergo a neutralization reaction, typically producing water and a salt. This process is fundamental in various applications, from maintaining the pH of human blood to industrial manufacturing and environmental remediation."
                ),
                Branch.CHEMISTRY
            )
        ) else listOf(
            TopicDetail(
                "c1", "पदार्थ की अवस्थाएं",
                listOf(
                    "पदार्थ मुख्य रूप से तीन अवस्थाओं में मौजूद होता है: ठोस, तरल और गैस। ठोस का एक निश्चित आकार और आयतन होता है क्योंकि उनके कण कसकर भरे होते हैं। तरल पदार्थों का एक निश्चित आयतन होता है लेकिन वे अपने बर्तन का आकार ले लेते हैं।",
                    "गैसों का न तो कोई निश्चित आकार होता है और न ही निश्चित आयतन। इन तीन के अलावा, वैज्ञानिक प्लाज्मा और बोस-आइंस्टीन कंडेनसेट का भी अध्ययन करते हैं।",
                    "चरण परिवर्तन तब होते हैं जब किसी पदार्थ से ऊर्जा जोड़ी या हटाई जाती है, जिससे वह एक अवस्था से दूसरी अवस्था में बदल जाता है। सामान्य परिवर्तनों में पिघलना, जमना, वाष्पीकरण और संघनन शामिल हैं।"
                ),
                Branch.CHEMISTRY
            ),
            TopicDetail(
                "c2", "तत्व और मिश्रण",
                listOf(
                    "शुद्ध पदार्थों में केवल एक ही प्रकार के कण होते हैं और इसमें तत्व और यौगिक शामिल होते हैं। तत्व पदार्थ का सबसे सरल रूप हैं, जबकि यौगिक दो या दो से अधिक तत्वों से बने पदार्थ हैं।",
                    "मिश्रण में दो या दो से अधिक पदार्थ भौतिक रूप से संयुक्त होते हैं और इन्हें सजातीय या विजातीय के रूप में वर्गीकृत किया जा सकता है। सजातीय मिश्रणों की संरचना एक समान होती है, जबकि विजातीय मिश्रणों में अलग-अलग घटक होते हैं।",
                    "मिश्रणों को अलग करने में भौतिक प्रक्रियाएं शामिल होती हैं जो क्वथनांक या घुलनशीलता जैसे गुणों में अंतर का फायदा उठाती हैं। सामान्य तकनीकों में निस्पंदन, आसवन और वाष्पीकरण शामिल हैं।"
                ),
                Branch.CHEMISTRY
            ),
            TopicDetail(
                "c3", "परमाणु संरचना",
                listOf(
                    "परमाणु एक रासायनिक तत्व की मूल इकाई है, जिसमें इलेक्ट्रॉनों के बादल से घिरा एक केंद्रीय नाभिक होता है। डाल्टन, थॉमसन, रदरफोर्ड और बोहर के मॉडलों ने हमारी समझ को परिष्कृत किया है।",
                    "नाभिक में प्रोटॉन होते हैं, जो धनात्मक आवेश ले जाते हैं, और न्यूट्रॉन होते हैं, जो तटस्थ होते हैं। प्रोटॉन की संख्या तत्व की पहचान (परमाणु संख्या) को परिभाषित करती है।",
                    "इलेक्ट्रॉन नकारात्मक रूप से आवेशित कण होते हैं जो विशिष्ट ऊर्जा स्तरों या कोशों में नाभिक की परिक्रमा करते हैं। इन इलेक्ट्रॉनों की व्यवस्था परमाणु की रासायनिक प्रतिक्रिया को निर्धारित करती है।"
                ),
                Branch.CHEMISTRY
            ),
            TopicDetail(
                "c4", "रासायनिक अभिक्रियाएं",
                listOf(
                    "एक रासायनिक अभिक्रिया वह प्रक्रिया है जो रासायनिक पदार्थों के एक समूह को दूसरे में बदलने की ओर ले जाती है। अभिकारक शुरुआती सामग्री हैं, जबकि उत्पाद परिणाम के रूप में बने नए पदार्थ हैं।",
                    "द्रव्यमान संरक्षण का नियम कहता है कि रासायनिक अभिक्रिया में द्रव्यमान न तो बनाया जाता है और न ही नष्ट किया जाता है। यह सिद्धांत रासायनिक समीकरणों को संतुलित करने की आवश्यकता पैदा करता है।",
                    "अभिक्रियाओं को ऊर्जा परिवर्तनों द्वारा वर्गीकृत किया जा सकता है: ऊष्माक्षेपी अभिक्रियाएं ऊर्जा मुक्त करती हैं, जबकि ऊष्माशोषी अभिक्रियाएं ऊर्जा अवशोषित करती हैं।"
                ),
                Branch.CHEMISTRY
            ),
            TopicDetail(
                "c5", "अम्ल और क्षार",
                listOf(
                    "अम्ल और क्षार रासायनिक यौगिकों के दो वर्ग हैं जो विपरीत गुण प्रदर्शित करते हैं। अम्ल आम तौर पर खट्टे होते हैं, जबकि क्षार अक्सर कड़वे होते हैं और छूने में फिसलन भरे महसूस होते हैं।",
                    "pH स्केल, 0 से 14 तक, मापता है कि कोई पदार्थ कितना अम्लीय या क्षारीय है। 7 का pH तटस्थ है, जबकि 7 से नीचे के मान अम्लीय हैं और 7 से ऊपर के मान क्षारीय हैं।",
                    "जब एक अम्ल और एक क्षार अभिक्रिया करते हैं, तो वे एक उदासीनीकरण अभिक्रिया से गुजरते हैं, जिससे पानी और एक लवण बनता है। यह प्रक्रिया मानव रक्त के pH को बनाए रखने के लिए मौलिक है।"
                ),
                Branch.CHEMISTRY
            )
        )
    }

    private fun getBiologyTopics(lang: Lang): List<TopicDetail> {
        return if (lang == Lang.EN) listOf(
            TopicDetail(
                "b1", "Cell Theory",
                listOf(
                    "Cell theory is a fundamental principle of biology which states that all living organisms are composed of one or more cells. Cells are the basic unit of structure and function in living things, and all cells arise from pre-existing cells.",
                    "Cells are categorized into two main types: prokaryotic and eukaryotic. Prokaryotic cells, like bacteria, lack a defined nucleus, while eukaryotic cells possess a nucleus and specialized membrane-bound organelles that perform specific tasks.",
                    "Organelles such as the mitochondria, ribosomes, and endoplasmic reticulum work together to maintain the cell's life processes. The cell membrane acts as a gatekeeper, controlling the movement of substances in and out of the cell to maintain homeostasis."
                ),
                Branch.BIOLOGY
            ),
            TopicDetail(
                "b2", "Plant Tissues",
                listOf(
                    "Plant tissues are specialized groups of cells that perform specific functions and are divided into meristematic and permanent tissues. Meristematic tissues are found in growing regions like root and shoot tips, where cells divide continuously.",
                    "Permanent tissues are derived from meristematic tissues that have lost the ability to divide. Simple permanent tissues like parenchyma and sclerenchyma provide support and storage, while complex tissues like xylem and phloem are involved in transport.",
                    "Xylem is responsible for the upward conduction of water and minerals from the roots to the leaves, while phloem transports the food produced during photosynthesis to various parts of the plant, ensuring its survival and growth."
                ),
                Branch.BIOLOGY
            ),
            TopicDetail(
                "b3", "Nutrition in Humans",
                listOf(
                    "Human nutrition involves the intake of food and its conversion into energy and nutrients required for life. The digestive system breaks down complex food substances into simpler, absorbable molecules through mechanical and chemical processes.",
                    "Enzymes play a crucial role in chemical digestion by accelerating the breakdown of proteins, carbohydrates, and fats. For instance, amylase in saliva starts breaking down starch, while pepsin in the stomach begins protein digestion.",
                    "Absorption of nutrients occurs primarily in the small intestine, where tiny finger-like projections called villi increase the surface area for efficient transfer into the bloodstream. Undigested waste is then eliminated through the large intestine."
                ),
                Branch.BIOLOGY
            ),
            TopicDetail(
                "b4", "Respiration",
                listOf(
                    "Respiration is the biochemical process by which living cells obtain energy by combining oxygen and glucose, resulting in the release of carbon dioxide, water, and ATP. It can be aerobic (with oxygen) or anaerobic (without oxygen).",
                    "Cellular respiration occurs in the mitochondria, often called the powerhouse of the cell. During this process, the energy stored in food is released in a controlled manner to power various cellular activities essential for the organism's survival.",
                    "Different organisms have specialized respiratory organs for gas exchange; humans use lungs, fish use gills, and plants use stomata in their leaves. These organs ensure a constant supply of oxygen and the efficient removal of carbon dioxide."
                ),
                Branch.BIOLOGY
            ),
            TopicDetail(
                "b5", "Reproduction in Plants",
                listOf(
                    "Reproduction in plants can occur through asexual or sexual means. Asexual reproduction involve methods like budding, fragmentation, and vegetative propagation, where a new plant is produced from a single parent without seeds.",
                    "Sexual reproduction in flowering plants involves the fusion of male and female gametes produced in the flower. The stamen is the male reproductive part, while the pistil is the female part containing the ovary where ovules are produced.",
                    "Pollination is the transfer of pollen from the anther to the stigma, often aided by wind, water, or insects. Following pollination, fertilization occurs when the pollen tube reaches the ovule, eventually leading to seed and fruit formation."
                ),
                Branch.BIOLOGY
            )
        ) else listOf(
            TopicDetail(
                "b1", "कोशिका सिद्धांत",
                listOf(
                    "कोशिका सिद्धांत जीव विज्ञान का एक मौलिक सिद्धांत है जो कहता है कि सभी जीवित जीव एक या अधिक कोशिकाओं से बने होते हैं। कोशिकाएं जीवित चीजों में संरचना और कार्य की मूल इकाई हैं।",
                    "कोशिकाओं को दो मुख्य प्रकारों में वर्गीकृत किया गया है: प्रोकैरियोटिक और यूकैरियोटिक। प्रोकैरियोटिक कोशिकाओं में एक निश्चित नाभिक की कमी होती है, जबकि यूकैरियोटिक कोशिकाओं में एक नाभिक होता है।",
                    "माइटोकॉन्ड्रिया और राइबोसोम जैसे अंग कोशिका की जीवन प्रक्रियाओं को बनाए रखने के लिए मिलकर काम करते हैं। कोशिका झिल्ली पदार्थों की आवाजाही को नियंत्रित करती है।"
                ),
                Branch.BIOLOGY
            ),
            TopicDetail(
                "b2", "पादप ऊतक",
                listOf(
                    "पादप ऊतक कोशिकाओं के विशिष्ट समूह होते हैं जो विशिष्ट कार्य करते हैं और इन्हें मेरिस्टेमैटिक और स्थायी ऊतकों में विभाजित किया जाता है।",
                    "स्थायी ऊतक मेरिस्टेमैटिक ऊतकों से प्राप्त होते हैं जिन्होंने विभाजित होने की क्षमता खो दी है। जाइलम और फ्लोएम जैसे जटिल ऊतक परिवहन में शामिल होते हैं।",
                    "जाइलम जड़ों से पत्तियों तक पानी और खनिजों के परिवहन के लिए जिम्मेदार है, जबकि फ्लोएम प्रकाश संश्लेषण के दौरान उत्पादित भोजन को पौधे के विभिन्न भागों में पहुँचाता है।"
                ),
                Branch.BIOLOGY
            ),
            TopicDetail(
                "b3", "मानव पोषण",
                listOf(
                    "मानव पोषण में भोजन का सेवन और जीवन के लिए आवश्यक ऊर्जा और पोषक तत्वों में इसका परिवर्तन शामिल है। पाचन तंत्र यांत्रिक और रासायनिक प्रक्रियाओं के माध्यम से भोजन को तोड़ता है।",
                    "एंजाइम प्रोटीन, कार्बोहाइड्रेट और वसा के टूटने को तेज करके रासायनिक पाचन में महत्वपूर्ण भूमिका निभाते हैं। लार में एमाइलेज स्टार्च को तोड़ना शुरू करता है।",
                    "पोषक तत्वों का अवशोषण मुख्य रूप से छोटी आंत में होता है। अपचित कचरे को फिर बड़ी आंत के माध्यम से बाहर निकाल दिया जाता है।"
                ),
                Branch.BIOLOGY
            ),
            TopicDetail(
                "b4", "श्वसन",
                listOf(
                    "श्वसन वह जैव रासायनिक प्रक्रिया है जिसके द्वारा जीवित कोशिकाएं ऑक्सीजन और ग्लूकोज को मिलाकर ऊर्जा प्राप्त करती हैं। यह एरोबिक या एनारोबिक हो सकता है।",
                    "कोशकीय श्वसन माइटोकॉन्ड्रिया में होता है। इस प्रक्रिया के दौरान, भोजन में संचित ऊर्जा कोशिका की विभिन्न गतिविधियों को शक्ति देने के लिए मुक्त होती है।",
                    "गैस विनिमय के लिए अलग-अलग जीवों में विशिष्ट श्वसन अंग होते हैं; मनुष्य फेफड़ों का उपयोग करते हैं, मछली गलफड़ों का उपयोग करती है, और पौधे रंध्रों का उपयोग करते हैं।"
                ),
                Branch.BIOLOGY
            ),
            TopicDetail(
                "b5", "पौधों में प्रजनन",
                listOf(
                    "पौधों में प्रजनन अलैंगिक या लैंगिक माध्यमों से हो सकता है। अलैंगिक प्रजनन में नवोदित और विखंडन जैसी विधियाँ शामिल हैं।",
                    "फूल वाले पौधों में लैंगिक प्रजनन में फूल में उत्पन्न नर और मादा युग्मकों का संलयन शामिल होता है। पुंकेसर नर हिस्सा है और स्त्रीकेसर मादा हिस्सा है।",
                    "परागण एंथर से स्टिग्मा तक पराग का स्थानांतरण है। परागण के बाद, निषेचन तब होता है जब पराग नली बीजांड तक पहुँचती है।"
                ),
                Branch.BIOLOGY
            )
        )
    }
}
