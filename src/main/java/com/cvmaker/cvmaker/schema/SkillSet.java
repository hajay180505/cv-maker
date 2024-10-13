package com.cvmaker.cvmaker.schema;

import java.util.List;

public class SkillSet {
    private String skillName;
    private List<String> skills;

    public SkillSet(String skillName, List<String> skills) {
        this.skillName = skillName;
        this.skills = skills;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
