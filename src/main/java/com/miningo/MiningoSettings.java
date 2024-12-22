package com.miningo;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class MiningoSettings {
    @SerializedName("language")
    public String lang;

    @SerializedName("disabled_chaos_effects")
    public List<String> disabledChaosEffects;
}