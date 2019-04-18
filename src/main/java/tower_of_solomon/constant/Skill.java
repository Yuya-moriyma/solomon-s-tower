package tower_of_solomon.constant;

public class Skill {
    public enum Timing {
        PLAYER_BATTLE_START("playerBattleStart"),
        PLAYER_TURN_START("playerTurnStart"),
        PLAYER_ATTACK("playerAttack"),
        PLAYER_ATTACK_SKILL("playerAttackSkill"),
        PLAYER_DAMAGED_SKILL("playerDamagedSkill"),
        PLAYER_TURN_END("playerTurnEnd"),
        PLAYER_BATTLE_END("playerBattleEnd"),
        ENEMY_BATTLE_START("enemyBattleStart"),
        ENEMY_TURN_START("enemyTurnStart"),
        ENEMY_ATTACK_SKILL("enemyAttackSkill"),
        ENEMY_ATTACK("enemyAttack"),
        ENEMY_DAMAGED("enemyDamaged"),
        ENEMY_TURN_END("enemyTurnEnd"),
        ENEMY_BATTLE_END("enemyBattleEnd");

        private final String text;

        Timing(final String text) {
            this.text = text;
        }

        public String toString() {
            return this.text;
        }
    }
}
