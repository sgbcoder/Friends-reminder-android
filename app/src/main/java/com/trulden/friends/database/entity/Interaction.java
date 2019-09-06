package com.trulden.friends.database.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Calendar;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "interaction_table",

        foreignKeys = {
        @ForeignKey(entity = InteractionType.class,
                    parentColumns = "id",
                    childColumns = "interactionTypeId",
                    onDelete = CASCADE)
        })

public class Interaction {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int interactionTypeId;

    @NonNull
    private Calendar date;

    private String comment;

    public Interaction(int interactionTypeId, @NonNull Calendar date, String comment) {
        this.interactionTypeId = interactionTypeId;
        this.date = date;
        this.comment = comment;
    }

    @Ignore
    public Interaction(int id, int interactionTypeId, @NonNull Calendar date, String comment) {
        this.id = id;
        this.interactionTypeId = interactionTypeId;
        this.date = date;
        this.comment = comment;
    }

    // -----------------------------------------
    // Getters and setters
    // -----------------------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInteractionTypeId() {
        return interactionTypeId;
    }

    public void setInteractionTypeId(int interactionTypeId) {
        this.interactionTypeId = interactionTypeId;
    }

    @NonNull
    public Calendar getDate() {
        return date;
    }

    public void setDate(@NonNull Calendar date) {
        this.date = date;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}