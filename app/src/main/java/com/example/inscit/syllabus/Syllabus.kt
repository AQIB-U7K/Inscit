package com.example.inscit.syllabus

import com.example.inscit.Branch
import com.example.inscit.models.Lang

data class NoteEntry(
    val title: String,
    val content: String,
    val branch: Branch,
    val lang: Lang
)

object Syllabus {
    fun getSyllabusNotes(branch: Branch, lang: Lang): List<NoteEntry> {
        val titles = when (branch) {
            Branch.PHYSICS -> if (lang == Lang.EN) listOf(
                "Motion Basics", "Newton's Laws", "Energy & Work", "Light Reflection", "Heat Transfer",
                "Wave Mechanics", "Mathematical Waves", "Wave Applications"
            ) else listOf(
                "गति की बुनियादी बातें", "न्यूटन के नियम", "ऊर्जा और कार्य", "प्रकाश परावर्तन", "ऊष्मा स्थानांतरण",
                "तरंग यांत्रिकी", "गणितीय तरंगें", "तरंग अनुप्रयोग"
            )
            Branch.CHEMISTRY -> if (lang == Lang.EN) listOf(
                "States of Matter", "Elements & Mixtures", "Atoms & Molecules", "Matter Changes", "Acids & Bases",
                "Bohr Model", "Quantum Numbers", "Modern Quantum"
            ) else listOf(
                "पदार्थ की अवस्थाएं", "तत्व और मिश्रण", "परमाणु और अणु", "पदार्थ परिवर्तन", "अम्ल और क्षार",
                "बोहर मॉडल", "क्वांटम संख्या", "आधुनिक क्वांटम"
            )
            Branch.BIOLOGY -> if (lang == Lang.EN) listOf(
                "Cell Structure", "Plant & Animal Tissues", "Life Nutrition", "Respiration", "Reproduction",
                "Cell Biology", "Cell Membrane", "Cell Division"
            ) else listOf(
                "कोशिका संरचना", "पादप और पशु ऊतक", "जीवन पोषण", "श्वसन और परिवहन", "प्रजनन",
                "कोशिका जीव विज्ञान", "कोशिका झिल्ली", "कोशिका विभाजन"
            )
        }
        
        val contents = when (branch) {
            Branch.PHYSICS -> PhysicsSyllabus.getExplanations(lang)
            Branch.CHEMISTRY -> ChemistrySyllabus.getExplanations(lang)
            Branch.BIOLOGY -> BiologySyllabus.getExplanations(lang)
        }
        
        return titles.zip(contents).map { (title, content) ->
            NoteEntry(title, content, branch, lang)
        }
    }
}
