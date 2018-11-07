
package com.vframedata.android.vframedata.Pojo.Zangief.Moves.Normal;

import javax.annotation.Generated;
import com.google.gson.annotations.SerializedName;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class Taunt {

    @SerializedName("airmove")
    private Boolean mAirmove;
    @SerializedName("cancelsTo")
    private String mCancelsTo;
    @SerializedName("cmnCmd")
    private String mCmnCmd;
    @SerializedName("followUp")
    private Boolean mFollowUp;
    @SerializedName("moveButton")
    private String mMoveButton;
    @SerializedName("moveMotion")
    private String mMoveMotion;
    @SerializedName("moveName")
    private String mMoveName;
    @SerializedName("numCmd")
    private String mNumCmd;
    @SerializedName("plnCmd")
    private String mPlnCmd;
    @SerializedName("projectile")
    private Boolean mProjectile;
    @SerializedName("recovery")
    private Long mRecovery;

    public Boolean getAirmove() {
        return mAirmove;
    }

    public void setAirmove(Boolean airmove) {
        mAirmove = airmove;
    }

    public String getCancelsTo() {
        return mCancelsTo;
    }

    public void setCancelsTo(String cancelsTo) {
        mCancelsTo = cancelsTo;
    }

    public String getCmnCmd() {
        return mCmnCmd;
    }

    public void setCmnCmd(String cmnCmd) {
        mCmnCmd = cmnCmd;
    }

    public Boolean getFollowUp() {
        return mFollowUp;
    }

    public void setFollowUp(Boolean followUp) {
        mFollowUp = followUp;
    }

    public String getMoveButton() {
        return mMoveButton;
    }

    public void setMoveButton(String moveButton) {
        mMoveButton = moveButton;
    }

    public String getMoveMotion() {
        return mMoveMotion;
    }

    public void setMoveMotion(String moveMotion) {
        mMoveMotion = moveMotion;
    }

    public String getMoveName() {
        return mMoveName;
    }

    public void setMoveName(String moveName) {
        mMoveName = moveName;
    }

    public String getNumCmd() {
        return mNumCmd;
    }

    public void setNumCmd(String numCmd) {
        mNumCmd = numCmd;
    }

    public String getPlnCmd() {
        return mPlnCmd;
    }

    public void setPlnCmd(String plnCmd) {
        mPlnCmd = plnCmd;
    }

    public Boolean getProjectile() {
        return mProjectile;
    }

    public void setProjectile(Boolean projectile) {
        mProjectile = projectile;
    }

    public Long getRecovery() {
        return mRecovery;
    }

    public void setRecovery(Long recovery) {
        mRecovery = recovery;
    }

}
