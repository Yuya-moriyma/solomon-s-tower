package service;

import android.app.Application;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import registory.CharacterRegistry;
import registory.ItemRegistry;
import constant.Character;
import entity.ButtonItem;
import model.*;
import util.ArrayUtil;
import util.LogUtil;
import util.MathUtil;
import util.StringUtil;

public class UserService {

    //region メソッド

    /**
     * ユーザーデータ取得
     * @return
     */
    public UserModel getUser() {
        PartyModel party = null;
        List itemList = null;
        TowerModel tower = null;
        int currentFloor = 0;
        int currentPoint = 0;
        List knownEnemyIds = null;
        try {
            HashMap<String, String> userData = this.getData();
            party = this.createParty(userData.get("party"));
            itemList = this.createItem(userData.get("item"));
            currentFloor = Integer.parseInt(userData.get("floor"));
            currentPoint = Integer.parseInt(userData.get("point"));
            tower = this.createTower(userData.get("floorConstruct"));
            knownEnemyIds = this.createKnownEnemyIds(userData.get("knownEnemyIds"));
        } catch (RuntimeException e) {
            LogUtil.printStackTrace(e);
        }
        return new UserModel(party, currentFloor, currentPoint, itemList, tower, knownEnemyIds);
    }

    /**
     * 敵のIDを覚えているかチェック
     * @param enemyId
     * @return
     */
    public boolean rememberEnemy(int enemyId) {
        boolean result = false;
        try {
            ApplicationModel app = ApplicationModel.getInstance();
            UserModel model = app.getUser();
            List knownEnemyId = model.getKnownEnemyId();
            result = knownEnemyId.contains(enemyId);;
        }
        catch (RuntimeException e) {
            LogUtil.printStackTrace(e);
            return result;
        }
        return false;
    }

    /**
     * 現在の階層を取得する
     * @return
     */
    public int getCurrentFloor() {
        int result = 0;
        try {
            ApplicationModel app = ApplicationModel.getInstance();
            UserModel model = app.getUser();
            result = model.getCurrentFloor();
        }
        catch (RuntimeException e) {
            LogUtil.printStackTrace(e);
            return result;
        }
        return result;
    }

    /**
     * 現在の序列ポイントを取得する
     * @return
     */
    public int getCurrentPoint() {
        int result = 0;
        try {
            ApplicationModel app = ApplicationModel.getInstance();
            UserModel model = app.getUser();
            result = model.getCurrentPoint();
        }
        catch (RuntimeException e) {
            LogUtil.printStackTrace(e);
            return result;
        }
        return result;
    }

    /**
     * 現在の序列ポイントを取得する
     * @return
     */
    public TowerModel getTower() {
        TowerModel result = null;
        try {
            ApplicationModel app = ApplicationModel.getInstance();
            UserModel model = app.getUser();
            result = model.getTower();
        }
        catch (RuntimeException e) {
            LogUtil.printStackTrace(e);
            return result;
        }
        return result;
    }

    /**
     * パーティ作成
     * @param partyIds
     * @return
     */
    private PartyModel createParty(String partyIds) {
        CharacterRegistry registry = new CharacterRegistry();
        List characters = new ArrayList<CharacterModel>();
        String[] ids = partyIds.split(",");
        for(String id : ids) {
            characters.add(registry.createCharacter(Integer.parseInt(id)));
        }
        return new PartyModel(characters);
    }

    /**
     * アイテム生成
     * @param itemIds
     * @return
     */
    private List<ItemModel> createItem(String itemIds) {
        ItemRegistry registry = new ItemRegistry();
        List list = new ArrayList<ItemModel>();
        String[] ids = itemIds.split(",");
        for(String id : ids) {
            list.add(registry.getItem(id));
        }
        return list;
    }

    /**
     * タワーモデル作成
     * @param floorConstruct
     * @return
     */
    private TowerModel createTower(String floorConstruct) {
        CharacterRegistry registry = new CharacterRegistry();
        List<CharacterModel> floorCharacters = registry.createCharacters(
                ArrayUtil.convertToTextBlock(floorConstruct));
        return new TowerModel(floorCharacters);
    }

    /**
     * 敵情報作成
     * @param enemyIds
     * @return
     */
    private List<Integer> createKnownEnemyIds(String enemyIds) {
        List list = new ArrayList<Integer>();
        String[] ids = enemyIds.split(",");
        for(String id : ids) {
            list.add(String.valueOf(id));
        }
        return list;
    }


    /**
     * ユーザーデータを読み込み
     * @return
     */
    private HashMap getData() {
        HashMap data = this.loadData();
        if(data != null) {
            return data;
        }
        return this.createNewData();
    }

    /**
     * ユーザーデータをDBから取得
     * @return
     */
    private HashMap loadData() {
        //TODO:DBからユーザーデータ取得
        CharacterRegistry registry = new CharacterRegistry();
        CharacterModel model = registry.createCharacter(72);
        int id = model.getStatus(Character.STATUS_ID);
        HashMap<String, String> newData = new HashMap<String, String>();
        newData.put("party", String.valueOf(id));
        newData.put("floor", "1");
        newData.put("point", "10");
        newData.put("item", "1,2,3");
        newData.put("floorConstruct", this.createFloorConstruct(id));
        newData.put("knownEnemyIds", "56,72,30");
        return newData;
    }


    /**
     * ユーザーデータを新規作成
     * @return
     */
    private HashMap createNewData() {
        int firstCharacter = MathUtil.getRandomValue(MathUtil.getRandomList(63,10));
        HashMap<String, String> newData = new HashMap<String, String>();
        newData.put("party", String.valueOf(firstCharacter));
        newData.put("floor", "1");
        newData.put("point", "0");
        newData.put("item", "2,1,3");
        newData.put("floorConstruct", this.createFloorConstruct(firstCharacter));
        newData.put("knownEnemyIds", "50,72,30");
        return newData;
    }

    /**
     * 階数作成
     * @return
     */
    private String createFloorConstruct(int firstCharacter) {
        ArrayList<Integer> list = new ArrayList(72);
        list.addAll(MathUtil.getRandomList(63,10));
        list.add(7);
        list.addAll(MathUtil.getRandomList(53,10));
        list.add(6);
        list.addAll(MathUtil.getRandomList(44,9));
        list.add(5);
        list.addAll(MathUtil.getRandomList(35,9));
        list.add(4);
        list.addAll(MathUtil.getRandomList(26,9));
        list.add(3);
        list.addAll(MathUtil.getRandomList(17,9));
        list.add(2);
        list.addAll(MathUtil.getRandomList(8,9));
        list.add(1);
        list.remove(list.indexOf(firstCharacter));
        list.remove(list.indexOf(55));
        return TextUtils.join(",",list);
    }

    /**
     * アイテムリスト取得
     * @return
     */
    public ArrayList<ButtonItem> getItemList() {
        try {
            ArrayList<ButtonItem>  itemList = new ArrayList<ButtonItem>();
            UserModel user = ApplicationModel.getUser();
            List<ItemModel> list = user.getItemList();
            int index = 0;
            for(ItemModel item : list) {
                itemList.add(new ButtonItem(index++, item.getName()));
            }
            return itemList;
        } catch (RuntimeException e) {
            LogUtil.printStackTrace(e);
            return null;
        }
    }
    //endregion
}
