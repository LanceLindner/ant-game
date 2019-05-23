package main.tiles;

import java.util.HashMap;

import main.audio.MusicType;

public enum TileType {
	WALL1(17, true),
	WALL2(18, true),
	WALL3(19, true),
	WALL4(20, true),
	WALL5(21, true),
	WALL6(22, true),
	WALL7(23, true),
	WALL8(24, true),

	SNORE1(33, "snore", 1),
	AHH1(34, "test", 2),

	SHADOW1(49),
	SHADOW2(50),
	SHADOW3(51),
	SHADOW4(52),
	SHADOW5(53),
	SHADOW6(54),
	SHADOW7(55),
	SHADOW8(56),

	GREEN_RESIDUE1(65),
	GREEN_RESIDUE2(66),
	GREEN_RESIDUE3(67),
	GREEN_RESIDUE4(68),
	GREEN_RESIDUE5(69),
	GREEN_RESIDUE6(70),
	GREEN_RESIDUE7(71),
	GREEN_RESIDUE8(72),

	YELLOW_RESIDUE1(81),
	YELLOW_RESIDUE2(82),
	YELLOW_RESIDUE3(83),
	YELLOW_RESIDUE4(84),
	YELLOW_RESIDUE5(85),
	YELLOW_RESIDUE6(86),
	YELLOW_RESIDUE7(87),
	YELLOW_RESIDUE8(88),

	NULL(97),

	GRASS1(129),
	GRASS2(130),
	GRASS3(131),
	GRASS4(132),
	GRASS5(133),
	GRASS6(134),
	GRASS7(135),
	GRASS8(136),

	HIVE1(145, true, true),

	CHEST1(513, true),
	CHEST2(514, true),
	CHEST3(515, true),
	CHEST4(516, true),
	CHEST5(517, true),
	CHEST6(518, true),
	CHEST7(519, true),
	CHEST8(520, true),
	CHEST9(521, true),
	CHEST10(522, true),
	CHEST11(523, true),
	CHEST12(524, true),
	CHEST13(525, true),
	CHEST14(526, true),
	CHEST15(527, true),
	CHEST16(528, true),
	CHEST17(529, true),
	CHEST18(530, true),
	CHEST19(531, true),
	CHEST20(532, true),
	CHEST21(533, true),
	CHEST22(534, true),
	CHEST23(535, true),
	CHEST24(536, true),
	CHEST25(537, true),
	CHEST26(538, true),
	CHEST27(539, true),
	CHEST28(540, true),
	CHEST29(541, true),
	CHEST30(542, true),
	CHEST31(543, true),
	CHEST32(544, true),
	CHEST33(545, true),
	CHEST34(546, true),
	CHEST35(547, true),
	CHEST36(548, true),
	CHEST37(549, true),
	CHEST38(550, true),
	CHEST39(551, true),
	CHEST40(552, true),
	CHEST41(553, true),
	CHEST42(554, true),
	CHEST43(555, true),
	CHEST44(556, true),
	CHEST45(557, true),
	CHEST46(558, true),
	CHEST47(559, true),
	CHEST48(560, true),
	CHEST49(561, true),
	CHEST50(562, true),
	CHEST51(563, true),
	CHEST52(564, true),
	CHEST53(565, true),
	CHEST54(566, true),
	CHEST55(567, true),
	CHEST56(568, true),
	CHEST57(569, true),
	CHEST58(570, true),
	CHEST59(571, true),
	CHEST60(572, true),
	CHEST61(573, true),
	CHEST62(574, true),
	CHEST63(575, true),
	CHEST64(576, true),
	CHEST65(577, true),
	CHEST66(578, true),
	CHEST67(579, true),
	CHEST68(580, true),
	CHEST69(581, true),
	CHEST70(582, true),
	CHEST71(583, true),
	CHEST72(584, true),
	CHEST73(585, true),
	CHEST74(586, true),
	CHEST75(587, true),
	CHEST76(588, true),
	CHEST77(589, true),
	CHEST78(590, true),
	CHEST79(591, true),
	CHEST80(592, true),
	CHEST81(593, true),
	CHEST82(594, true),
	CHEST83(595, true),
	CHEST84(596, true),
	CHEST85(597, true),
	CHEST86(598, true),
	CHEST87(599, true),
	CHEST88(600, true),
	CHEST89(601, true),
	CHEST90(602, true),
	CHEST91(603, true),
	CHEST92(604, true),
	CHEST93(605, true),
	CHEST94(606, true),
	CHEST95(607, true),
	CHEST96(608, true),
	CHEST97(609, true),
	CHEST98(610, true),
	CHEST99(611, true),
	CHEST100(612, true),
	CHEST101(613, true),
	CHEST102(614, true),
	CHEST103(615, true),
	CHEST104(616, true),
	CHEST105(617, true),
	CHEST106(618, true),
	CHEST107(619, true),
	CHEST108(620, true),
	CHEST109(621, true),
	CHEST110(622, true),
	CHEST111(623, true),
	CHEST112(624, true),
	CHEST113(625, true),
	CHEST114(626, true),
	CHEST115(627, true),
	CHEST116(628, true),
	CHEST117(629, true),
	CHEST118(630, true),
	CHEST119(631, true),
	CHEST120(632, true),
	CHEST121(633, true),
	CHEST122(634, true),
	CHEST123(635, true),
	CHEST124(636, true),
	CHEST125(637, true),
	CHEST126(638, true),
	CHEST127(639, true),
	CHEST128(640, true),;

	private final int id;
	private boolean solid = false;
	private boolean hive = false;

	private String musicName;

	private MusicType musicType;

	private TileType(int id) {
		this.id = id;
	}

	private TileType(int id, boolean solid) {
		this.id = id;
		this.solid = solid;
	}

	private TileType(int id, boolean solid, boolean hive) {
		this.id = id;
		this.solid = solid;
		this.hive = hive;
	}

	private TileType(int id, String musicName, int musicTypeId) {
		this.id = id;
		this.musicName = musicName;
		this.musicType = MusicType.getMusicTypeById(musicTypeId);
	}

	public int getID() {
		return id;
	}

	public boolean isSolid() {
		return solid;
	}

	public boolean isHive() {
		return hive;
	}

	public String getMusicName() {
		return musicName;
	}

	public MusicType getMusicType() {
		return musicType;
	}

	private static HashMap<Integer, TileType> tileMap;

	static {
		tileMap = new HashMap<Integer, TileType>();
		for (TileType tileType : TileType.values()) {
			tileMap.put(tileType.id, tileType);
		}
	}

	public static TileType getTileTypeById(int id) {
		return tileMap.get(id);
	}
}
