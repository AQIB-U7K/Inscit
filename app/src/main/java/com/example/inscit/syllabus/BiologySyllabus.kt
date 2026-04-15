package com.example.inscit.syllabus

import com.example.inscit.models.Lang

object BiologySyllabus {
    fun getExplanations(lang: Lang): List<String> {
        return when (lang) {
            Lang.EN -> listOf(
                "Biology exploration begins with the Cell, life's basic unit. Plant cells have rigid cell walls and chloroplasts for photosynthesis, while animal cells lack these but have centrioles for division. Key organelles include the Nucleus (DNA storage), Mitochondria (energy powerhouse), and Ribosomes (protein synthesis).",
                "Tissues are groups of similar cells working together. In plants, Meristematic tissue enables growth, while Permanent tissues like Xylem and Phloem transport water and food. Animals have four main types: Epithelial (protection), Connective (support), Muscular (movement), and Nervous (control).",
                "Nutrition is how organisms obtain energy. Plants are autotrophs, using Photosynthesis to convert sunlight, CO2, and water into glucose. Animals are heterotrophs, requiring a Digestion system to break down complex food into absorbable nutrients like carbohydrates, proteins, and fats.",
                "Respiration and Transport systems sustain life. Breathing brings in oxygen for cellular respiration, which releases energy. In humans, the Circulatory system (heart, blood, vessels) acts as a transport network, delivering oxygen and nutrients to every cell while removing waste products like CO2.",
                "Reproduction ensures species continuity. Asexual reproduction involves one parent (e.g., budding), while Sexual reproduction combines gametes from two parents for genetic diversity. Heredity is the study of traits passed from parents to offspring via Genes, determining characteristics like height or color.",
                "Cell biology unveils life's fundamental organizational unit. Eukaryotic cells contain membrane-bound organelles, each performing specialized functions. The nucleus houses genetic material, mitochondria generate ATP, endoplasmic reticulum synthesizes proteins and lipids, and Golgi apparatus packages molecules for transport.",
                "The cell membrane acts as a selective barrier controlling molecular traffic through protein channels and pumps. Cytoskeleton provides structural support and facilitates intracellular transport via motor proteins. Cellular respiration in mitochondria converts glucose to ATP through glycolysis, Krebs cycle, and electron transport chain.",
                "Cell division through mitosis ensures genetic continuity, while meiosis creates genetic diversity in sexual reproduction. Apoptosis maintains tissue homeostasis. Understanding cellular mechanisms is crucial for medicine, biotechnology, and treating diseases from cancer to genetic disorders."
            )
            Lang.HI -> listOf(
                "जीव विज्ञान की खोज कोशिका से शुरू होती है, जो जीवन की मूल इकाई है। पादप कोशिकाओं में प्रकाश संश्लेषण के लिए कठोर कोशिका भित्ति और क्लोरोप्लास्ट होते हैं, जबकि पशु कोशिकाओं में इनकी कमी होती है। मुख्य अंगकों में नाभिक (DNA भंडारण), माइटोकॉन्ड्रिया (ऊर्जा घर), और राइबोसोम (प्रोटीन संश्लेषण) शामिल हैं।",
                "ऊतक समान कोशिकाओं के समूह होते हैं जो एक साथ काम करते हैं। पौधों में, विभज्योतक ऊतक वृद्धि को सक्षम बनाता है, जबकि जाइलम और फ्लोएम जैसे स्थायी ऊतक पानी और भोजन का परिवहन करते हैं। जानवरों में चार मुख्य प्रकार होते हैं: उपकला (संरक्षण), संयोजी (समर्थन), पेशीय (गति), और तंत्रिका (नियंत्रण)।",
                "पोषण वह तरीका है जिससे जीव ऊर्जा प्राप्त करते हैं। पौधे स्वपोषी होते हैं, जो सूर्य के प्रकाश, CO2 और पानी को ग्लूकोज में बदलने के लिए प्रकाश संश्लेषण का उपयोग करते हैं। जानवर परपोषी होते हैं, जिन्हें जटिल भोजन को अवशोषित पोषक तत्वों में तोड़ने के लिए पाचन तंत्र की आवश्यकता होती है।",
                "श्वसन और परिवहन प्रणाली जीवन को बनाए रखती हैं। श्वास कोशिकीय श्वसन के लिए ऑक्सीजन लाती है, जो ऊर्जा मुक्त करती है। मनुष्यों में, संचार प्रणाली (हृदय, रक्त, वाहिकाएं) एक परिवहन नेटवर्क के रूप में कार्य करती है, जो हर कोशिका को ऑक्सीजन और पोषक तत्व पहुंचाती है।",
                "प्रजनन प्रजातियों की निरंतरता सुनिश्चित करता है। अलैंगिक प्रजनन में एक जनक (जैसे, मुकुलन) शामिल होता है, जबकि लैंगिक प्रजनन आनुवंशिक विविधता के लिए दो जनकों के युग्मकों को जोड़ता है। आनुवंशिकता जीन के माध्यम से माता-पिता से संतानों में जाने वाले लक्षणों का अध्ययन है।",
                "कोशिका जीव विज्ञान जीवन की मौलिक संगठनात्मक इकाई का अनावरण करता है। यूकैरियोटिक कोशिकाओं में झिल्ली-बद्ध अंगक होते हैं, प्रत्येक विशेष कार्य करते हैं। नाभिक आनुवंशिक सामग्री रखता है, माइटोकॉन्ड्रिया ATP उत्पन्न करते हैं, एंडोप्लाज्मिक रेटिकुलम प्रोटीन और लिपिड संश्लेषित करता है।",
                "कोशिका झिल्ली एक चयनात्मक अवरोध के रूप में कार्य करती है जो प्रोटीन चैनलों और पंपों के माध्यम से आणविक यातायात को नियंत्रित करती है। साइटोस्केलेटन संरचनात्मक समर्थन प्रदान करता है और मोटर प्रोटीन के माध्यम से इंट्रासेल्युलर परिवहन की सुविधा प्रदान करता है।",
                "मािटोसिस के माध्यम से कोशिका विभाजन आनुवंशिक निरंतरता सुनिश्चित करता है, जबकि अर्धसूत्रीविभाजन यौन प्रजनन में आनुवंशिक विविधता पैदा करता है। एपोप्टोसिस ऊतक होमियोस्टैसिस बनाए रखता है। कोशिकीय तंत्र को समझना चिकित्सा और जैव प्रौद्योगिकी के लिए महत्वपूर्ण है।"
            )
        }
    }
}
