package com.example.inscit.syllabus

import com.example.inscit.models.Lang

object ChemistrySyllabus {
    fun getExplanations(lang: Lang): List<String> {
        return when (lang) {
            Lang.EN -> listOf(
                "Matter is anything that has mass and takes up space. It exists in three primary states: Solids (fixed shape/volume), Liquids (fixed volume, variable shape), and Gases (variable shape/volume). Changes between these states occur through heating or cooling.",
                "Pure matter consists of Elements (one type of atom) or Compounds (two or more elements chemically combined). Mixtures, however, contain substances physically mixed but not chemically bonded, and can be homogeneous or heterogeneous.",
                "Atoms are the basic building blocks of matter, consisting of protons, neutrons, and electrons. Molecules form when two or more atoms bond together. Understanding atomic structure is the key to understanding how all substances behave and interact.",
                "Changes in matter are either Physical or Chemical. Physical changes (like melting ice) alter the form but not the identity of a substance. Chemical changes (like rusting iron) result in the formation of entirely new substances with different properties.",
                "Acids, Bases, and Salts are fundamental chemical categories. Acids (like lemon juice) taste sour and turn litmus red, while Bases (like soap) feel slippery and turn litmus blue. When they neutralize each other, they form Salts and water.",
                "The Bohr model revolutionized atomic theory by introducing quantized energy levels. Electrons occupy discrete shells with specific energies, and transitions between levels emit or absorb photons of precise wavelengths. This explains atomic spectra, the unique fingerprint of each element.",
                "Quantum numbers (n, l, m, s) describe electron states in atoms. The principal quantum number n determines energy level and orbital size. Electron configuration follows the Aufbau principle, Pauli exclusion, and Hund's rule. For shell capacity, the classic pattern is 2n², giving K=2, L=8, M=18, N=32 and so on.",
                "Modern quantum mechanics extends beyond Bohr's picture using wave functions and probability distributions. Orbitals represent regions where electrons are likely found. Chemical bonding, periodic trends, and spectroscopy all build on quantum atomic theory. Understanding electron arrangements explains reactivity, compound formation, and material properties."
            )
            Lang.HI -> listOf(
                "पदार्थ वह है जिसमें द्रव्यमान होता है और जो स्थान घेरता है। यह तीन मुख्य अवस्थाओं में मौजूद होता है: ठोस (निश्चित आकार/आयतन), तरल (निश्चित आयतन, परिवर्तनशील आकार), और गैस (परिवर्तनशील आकार/आयतन)।",
                "शुद्ध पदार्थ में तत्व (एक प्रकार का परमाणु) या यौगिक (रासायनिक रूप से संयुक्त दो या अधिक तत्व) शामिल होते हैं। मिश्रण में वे पदार्थ होते हैं जो भौतिक रूप से मिश्रित होते हैं लेकिन रासायनिक रूप से बंधे नहीं होते हैं।",
                "परमाणु पदार्थ के बुनियादी निर्माण खंड हैं, जिनमें प्रोटॉन, न्यूट्रॉन और इलेक्ट्रॉन होते हैं। अणु तब बनते हैं जब दो या दो से अधिक परमाणु एक साथ जुड़ते हैं। परमाणु संरचना को समझना यह समझने की कुंजी है कि सभी पदार्थ कैसे व्यवहार करते हैं।",
                "पदार्थ में परिवर्तन या तो भौतिक होते हैं या रासायनिक। भौतिक परिवर्तन (जैसे बर्फ पिघलना) पदार्थ के रूप को बदलते हैं लेकिन उसकी पहचान को नहीं। रासायनिक परिवर्तन (जैसे लोहे में जंग लगना) के परिणामस्वरूप पूरी तरह से नए पदार्थ बनते हैं।",
                "अम्ल, क्षार और लवण मौलिक रासायनिक श्रेणियां हैं। अम्ल (जैसे नींबू का रस) स्वाद में खट्टे होते हैं और लिटमस को लाल कर देते हैं, जबकि क्षार (जैसे साबुन) चिकने महसूस होते हैं और लिटमस को नीला कर देते हैं।",
                "बोहर मॉडल ने परमाणु सिद्धांत में क्रांति ला दी, क्वांटीकृत ऊर्जा स्तरों को पेश करके। इलेक्ट्रॉन विशिष्ट ऊर्जा वाली असतत शेल्स में होते हैं, और स्तरों के बीच संक्रमण सटीक तरंग दैर्घ्य के फोटॉन उत्सर्जित या अवशोषित करते हैं।",
                "क्वांटम संख्याएं (n, l, m, s) परमाणुओं में इलेक्ट्रॉन अवस्थाओं का वर्णन करती हैं। मुख्य क्वांटम संख्या n ऊर्जा स्तर और कक्षीय आकार निर्धारित करती है। शेल क्षमता के लिए पारंपरिक पैटर्न 2n² होता है, इसलिए K=2, L=8, M=18, N=32 होता.।",
                "आधुनिक क्वांटम यांत्रिकी तरंग कार्यों और प्रायिकता वितरण का उपयोग करके बोहर की तस्वीर से आगे बढ़ती है। कक्षक उन क्षेत्रों का प्रतिनिधित्व करते हैं जहां इलेक्ट्रॉन पाए जाने की संभावना है। रासायनिक बंधन, आवर्त प्रवृत्तियाँ और स्पेक्ट्रोस्कोपी सभी क्वांटम परमाणु सिद्धांत पर निर्मित हैं।"
            )
        }
    }
}
