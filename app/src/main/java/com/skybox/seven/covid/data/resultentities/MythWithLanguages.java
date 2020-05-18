package com.skybox.seven.covid.data.resultentities;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.skybox.seven.covid.data.entities.Language;
import com.skybox.seven.covid.data.entities.Myth;

import java.util.List;

public class MythWithLanguages {
    @Embedded
    public Language language;
    @Relation( parentColumn = "languageId", entityColumn = "languageOwnerId")
    public List<Myth> mythList;
}
