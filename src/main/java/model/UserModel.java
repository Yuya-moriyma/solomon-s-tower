package model;

import java.util.ArrayList;
import java.util.List;

import constant.Character;

public class UserModel {

    //region フィールド

    private PartyModel party;
    private int currentFloor;
    private int currentPoint;
    private List itemList;
    private TowerModel tower;
    private List knownEnemyId;

    //endregion

    //region コンストラクタ

    /**
     * コンストラクタ
     * @param party
     * @param currentFloor
     * @param currentPoint
     * @param itemList
     */
    public UserModel(
            PartyModel party,
            int currentFloor,
            int currentPoint,
            List itemList,
            TowerModel towerModel,
            List knownEnemyId) {
        this.party = party;
        this.currentFloor = currentFloor;
        this.currentPoint = currentPoint;
        this.itemList = itemList;
        this.tower = towerModel;
        this.knownEnemyId = knownEnemyId;
    }
    //endregion

    //region アクセサー
    public PartyModel getParty() {
        return party;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public int getCurrentPoint() {
        return currentPoint;
    }

    public List getItemList() {
        return itemList;
    }

    public TowerModel getTower() {
        return tower;
    }

    public List getKnownEnemyId() { return knownEnemyId; }
    //endregion
}
