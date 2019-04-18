package tower_of_solomon.constant;

import tower_of_solomon.entity.QueryContentValue;

public class Database {
    public static final String DB_NAME = "SolomonData.db";
    public static final int DB_VERSION = 7;
    public enum Type {
        NULL("null"),
        INTEGER("integer"),
        REAL("real"),
        TEXT("text"),
        BLOB("blob");

        private final String text;

        Type(final String text) {
            this.text = text;
        }

        public String toString() {
            return this.text;
        }
    }
    public static final int QUERY_TYPE_INSERT = 0;
    public static final int QUERY_TYPE_SELECT = 1;
    public static final int QUERY_TYPE_UPDATE = 2;
    public static final int QUERY_TYPE_DELETE = 3;
    public static final String TABLE_NAME_CHARACTER = "m_character";
    public static final String TABLE_NAME_USER = "t_user";
    public static final String TABLE_NAME_BATTLE = "t_battle";
    public static final String TABLE_NAME_SKILL_TIMER = "t_skill_timer";
    public static final String TABLE_NAME_ITEM = "m_item";
    public static final QueryContentValue[] ddl = {
            new QueryContentValue().put("id",0).put("name","????").put("type",0).put("hp",0).put("atk",0).put("skill_detail","????"),
            new QueryContentValue().put("id",19).put("name","サロス").put("type",3).put("hp",117).put("atk",48).put("skill_detail","50%の確率でHPの1/4の値を回復し、50%の確率で攻撃が1.5倍になる"),
            new QueryContentValue().put("id",20).put("name","プルソン").put("type",4).put("hp",93).put("atk",55).put("skill_detail","戦闘終了後、序列ポイントを100得る"),
            new QueryContentValue().put("id",21).put("name","マラクス").put("type",4).put("hp",87).put("atk",60).put("skill_detail","相手のスキルを無効化する"),
            new QueryContentValue().put("id",22).put("name","イポス").put("type",4).put("hp",119).put("atk",36).put("skill_detail","2ターン後、攻撃が3倍になる"),
            new QueryContentValue().put("id",23).put("name","アイム").put("type",1).put("hp",87).put("atk",48).put("skill_detail","毎ターン、攻撃が6上がる"),
            new QueryContentValue().put("id",24).put("name","ナベリウス").put("type",2).put("hp",124).put("atk",31).put("skill_detail","序列が3の倍数の悪魔のスキルを封じる"),
            new QueryContentValue().put("id",25).put("name","グラ-シャ･ラボラス").put("type",1).put("hp",86).put("atk",69).put("skill_detail","毎ターン、相手の体力を16削る"),
            new QueryContentValue().put("id",26).put("name","ブーネ").put("type",4).put("hp",90).put("atk",52).put("skill_detail","30%の確率で攻撃を無効化し、30%の確率で相手の体力の1/4を削る"),
            new QueryContentValue().put("id",27).put("name","ロノウェ").put("type",3).put("hp",107).put("atk",39).put("skill_detail","50%の確率で相手の攻撃を無効化する"),
            new QueryContentValue().put("id",28).put("name","ベリス").put("type",4).put("hp",73).put("atk",61).put("skill_detail","攻撃する時、自分のHPの1/8の値を攻撃に加える"),
            new QueryContentValue().put("id",29).put("name","アスタロト").put("type",0).put("hp",66).put("atk",66).put("skill_detail","攻撃する時、30%の確率で攻撃が2倍になり、自分より上位の悪魔からのダメージを半減する"),
            new QueryContentValue().put("id",30).put("name","フォルネウス").put("type",2).put("hp",51).put("atk",58).put("skill_detail","水の属性以外の悪魔へ攻撃する時、攻撃が2倍"),
            new QueryContentValue().put("id",31).put("name","フォラス").put("type",4).put("hp",90).put("atk",50).put("skill_detail","全ての属性の悪魔から受けるダメージを1/2にする"),
            new QueryContentValue().put("id",32).put("name","アスモダイ").put("type",1).put("hp",57).put("atk",76).put("skill_detail","20%の確率で攻撃が失敗するが、20%の確率で攻撃する悪魔のスキルと属性効果を無効化する"),
            new QueryContentValue().put("id",33).put("name","ガープ").put("type",1).put("hp",91).put("atk",59).put("skill_detail","相手のスキルの効果を受けない"),
            new QueryContentValue().put("id",34).put("name","フルフル").put("type",1).put("hp",89).put("atk",56).put("skill_detail","全ての悪魔の属性効果を打ち消す"),
            new QueryContentValue().put("id",35).put("name","マルコシアス").put("type",1).put("hp",71).put("atk",62).put("skill_detail","毎ターン、攻撃が2回になる"),
            new QueryContentValue().put("id",36).put("name","ストラス").put("type",4).put("hp",94).put("atk",48).put("skill_detail","毎ターン、HPの1/2の値分を回復する"),
            new QueryContentValue().put("id",37).put("name","フェネクス").put("type",1).put("hp",81).put("atk",43).put("skill_detail","毎ターン、自分の体力を10回復し、相手の体力を10削る"),
            new QueryContentValue().put("id",38).put("name","ハルファス").put("type",3).put("hp",63).put("atk",50).put("skill_detail","毎ターン20%の確率でランダムにアイテムを1つ手に入れる"),
            new QueryContentValue().put("id",39).put("name","マルファス").put("type",3).put("hp",50).put("atk",63).put("skill_detail","最初のターンのみ、属性効果を受けず、攻撃が2倍になる"),
            new QueryContentValue().put("id",40).put("name","ラウム").put("type",3).put("hp",76).put("atk",44).put("skill_detail","自分より上位の悪魔に攻撃する時、攻撃が2倍になり、スキルの効果を受けない"),
            new QueryContentValue().put("id",41).put("name","フォカロル").put("type",3).put("hp",64).put("atk",59).put("skill_detail","火の属性効果を打ち消し、与えるダメージが2倍になる"),
            new QueryContentValue().put("id",42).put("name","ヴェパール").put("type",2).put("hp",74).put("atk",43).put("skill_detail","3ターンの間、相手のHPを20ずつ削る"),
            new QueryContentValue().put("id",43).put("name","サブノック").put("type",2).put("hp",82).put("atk",39).put("skill_detail","戦闘開始時、アイテムを1つ手に入れる"),
            new QueryContentValue().put("id",44).put("name","シャックス").put("type",3).put("hp",68).put("atk",52).put("skill_detail","40%の確率でダメージを反射する"),
            new QueryContentValue().put("id",45).put("name","ヴィネー").put("type",4).put("hp",79).put("atk",40).put("skill_detail","3ターンの間、受けるダメージが1/2になり、全ての属性効果を無効化する"),
            new QueryContentValue().put("id",46).put("name","ビフロンス").put("type",1).put("hp",57).put("atk",48).put("skill_detail","30%の確率で敵の攻撃を無効化し、30%の確率で攻撃が2倍になる"),
            new QueryContentValue().put("id",47).put("name","ウヴァル").put("type",4).put("hp",82).put("atk",22).put("skill_detail","全ての属性の悪魔から受けるダメージを1/2にする"),
            new QueryContentValue().put("id",48).put("name","ハアゲンティ").put("type",3).put("hp",72).put("atk",41).put("skill_detail","バトルに勝利した時、貰える序列ポイントが2倍になる"),
            new QueryContentValue().put("id",49).put("name","クローセル").put("type",2).put("hp",54).put("atk",25).put("skill_detail","火属性の悪魔に攻撃する時、攻撃が2倍になり、受けるダメージが1/2になる"),
            new QueryContentValue().put("id",50).put("name","フルカス").put("type",2).put("hp",61).put("atk",32).put("skill_detail","風属性の悪魔に攻撃する時、攻撃が2倍になる"),
            new QueryContentValue().put("id",51).put("name","バラム").put("type",4).put("hp",43).put("atk",51).put("skill_detail","自分の攻撃時、相手のスキルを無効化する"),
            new QueryContentValue().put("id",52).put("name","アロセス").put("type",4).put("hp",65).put("atk",40).put("skill_detail","HPが2倍になる"),
            new QueryContentValue().put("id",53).put("name","カイム").put("type",3).put("hp",39).put("atk",23).put("skill_detail","戦闘開始時、相手のHPを3/4にする"),
            new QueryContentValue().put("id",54).put("name","ムルムル").put("type",3).put("hp",56).put("atk",26).put("skill_detail","自分より上位の悪魔に攻撃する時、攻撃が2倍"),
            new QueryContentValue().put("id",55).put("name","オロバス").put("type",4).put("hp",33).put("atk",32).put("skill_detail","アイテム使用時、所持数が減らない"),
            new QueryContentValue().put("id",56).put("name","グレモリー").put("type",2).put("hp",46).put("atk",20).put("skill_detail","50%の確率で、1ターン相手のスキルを無効化する"),
            new QueryContentValue().put("id",57).put("name","オセー").put("type",4).put("hp",30).put("atk",40).put("skill_detail","50%の確率で反撃する。"),
            new QueryContentValue().put("id",58).put("name","アミー").put("type",1).put("hp",45).put("atk",10).put("skill_detail","30%の確率でダメージを吸収する"),
            new QueryContentValue().put("id",59).put("name","オリアス").put("type",2).put("hp",43).put("atk",35).put("skill_detail","相手のスキル発動率を1%にする。"),
            new QueryContentValue().put("id",60).put("name","ヴァプラ").put("type",3).put("hp",38).put("atk",30).put("skill_detail","相手のスキルを自分のスキルにする"),
            new QueryContentValue().put("id",61).put("name","ザガン").put("type",3).put("hp",44).put("atk",25).put("skill_detail","バトルに勝利した時、貰える序列ポイントが2倍になる"),
            new QueryContentValue().put("id",62).put("name","ヴォラク").put("type",3).put("hp",52).put("atk",30).put("skill_detail","バトルに勝利すると、必ずアイテムをドロップする"),
            new QueryContentValue().put("id",63).put("name","アンドラス").put("type",4).put("hp",50).put("atk",24).put("skill_detail","毎ターン、相手のHPを10削る。"),
            new QueryContentValue().put("id",64).put("name","フラロウス").put("type",1).put("hp",28).put("atk",16).put("skill_detail","40%の確率で攻撃を2倍にする"),
            new QueryContentValue().put("id",65).put("name","アンドレアルフス").put("type",2).put("hp",24).put("atk",10).put("skill_detail","3ターンの間、相手のスキルを封じる"),
            new QueryContentValue().put("id",66).put("name","キマリス").put("type",4).put("hp",24).put("atk",12).put("skill_detail","2ターンの間、受けるダメージが1/2になり、攻撃が2倍になる"),
            new QueryContentValue().put("id",67).put("name","アムドゥシアス").put("type",3).put("hp",40).put("atk",10).put("skill_detail","毎ターン体力を20回復する"),
            new QueryContentValue().put("id",68).put("name","ベリアル").put("type",3).put("hp",40).put("atk",12).put("skill_detail","土属性の悪魔からのダメージを1/3にし、与えるダメージが3倍になる"),
            new QueryContentValue().put("id",69).put("name","デカラビア").put("type",1).put("hp",14).put("atk",16).put("skill_detail","20%の確率で攻撃が5倍になる"),
            new QueryContentValue().put("id",70).put("name","セエレ").put("type",3).put("hp",20).put("atk",10).put("skill_detail","50%の確率でダメージを無効化する"),
            new QueryContentValue().put("id",71).put("name","ダンダリオン").put("type",2).put("hp",23).put("atk",8).put("skill_detail","75%の確率で相手の序列の値分のダメージを与える"),
            new QueryContentValue().put("id",72).put("name","アンドロマリウス").put("type",1).put("hp",20).put("atk",6).put("skill_detail","攻撃時、与えるダメージが二倍になる。")
    };
}
