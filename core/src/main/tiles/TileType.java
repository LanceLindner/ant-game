package main.tiles;

import java.util.HashMap;

import main.audio.MusicType;

public enum TileType {
	WALL1(17, true),
	WALL2(18, true),
	WALL3(19, true),
	WALL4(20, true),

	SNORE1(33, "snore", 1),
	AHH1(34, "test", 2),

	SHADOW1(49),
	SHADOW2(50),
	SHADOW3(51),
	SHADOW4(52),
	SHADOW5(53),
	SHADOW6(54),

	GREEN_RESIDUE1(65),
	GREEN_RESIDUE2(66),
	GREEN_RESIDUE3(67),
	GREEN_RESIDUE4(68),

	YELLOW_RESIDUE1(81),
	YELLOW_RESIDUE2(82),
	YELLOW_RESIDUE3(83),
	YELLOW_RESIDUE4(84),

	NULL(97),

	GRASS1(113),
	GRASS2(114),
	GRASS3(115),
	GRASS4(116),
	GRASS5(117),
	GRASS6(118),
	GRASS7(119),
	GRASS8(120),

	CHEST1(257, true),
	CHEST2(258, true),
	CHEST3(259, true),
	CHEST4(260, true),
	CHEST5(261, true),
	CHEST6(262, true),
	CHEST7(263, true),
	CHEST8(264, true),
	CHEST9(265, true),
	CHEST10(266, true),
	CHEST11(267, true),
	CHEST12(268, true),
	CHEST13(269, true),
	CHEST14(270, true),
	CHEST15(271, true),
	CHEST16(272, true),
	CHEST17(273, true),
	CHEST18(274, true),
	CHEST19(275, true),
	CHEST20(276, true),
	CHEST21(277, true),
	CHEST22(278, true),
	CHEST23(279, true),
	CHEST24(280, true),
	CHEST25(281, true),
	CHEST26(282, true),
	CHEST27(283, true),
	CHEST28(284, true),
	CHEST29(285, true),
	CHEST30(286, true),
	CHEST31(287, true),
	CHEST32(288, true),
	CHEST33(289, true),
	CHEST34(290, true),
	CHEST35(291, true),
	CHEST36(292, true),
	CHEST37(293, true),
	CHEST38(294, true),
	CHEST39(295, true),
	CHEST40(296, true),
	CHEST41(297, true),
	CHEST42(298, true),
	CHEST43(299, true),
	CHEST44(300, true),
	CHEST45(301, true),
	CHEST46(302, true),
	CHEST47(303, true),
	CHEST48(304, true),
	CHEST49(305, true),
	CHEST50(306, true),
	CHEST51(307, true),
	CHEST52(308, true),
	CHEST53(309, true),
	CHEST54(310, true),
	CHEST55(311, true),
	CHEST56(312, true),
	CHEST57(313, true),
	CHEST58(314, true),
	CHEST59(315, true),
	CHEST60(316, true),
	CHEST61(317, true),
	CHEST62(318, true),
	CHEST63(319, true),
	CHEST64(320, true),
	CHEST65(321, true),
	CHEST66(322, true),
	CHEST67(323, true),
	CHEST68(324, true),
	CHEST69(325, true),
	CHEST70(326, true),
	CHEST71(327, true),
	CHEST72(328, true),
	CHEST73(329, true),
	CHEST74(330, true),
	CHEST75(331, true),
	CHEST76(332, true),
	CHEST77(333, true),
	CHEST78(334, true),
	CHEST79(335, true),
	CHEST80(336, true),
	CHEST81(337, true),
	CHEST82(338, true),
	CHEST83(339, true),
	CHEST84(340, true),
	CHEST85(341, true),
	CHEST86(342, true),
	CHEST87(343, true),
	CHEST88(344, true),
	CHEST89(345, true),
	CHEST90(346, true),
	CHEST91(347, true),
	CHEST92(348, true),
	CHEST93(349, true),
	CHEST94(350, true),
	CHEST95(351, true),
	CHEST96(352, true),
	CHEST97(353, true),
	CHEST98(354, true),
	CHEST99(355, true),
	CHEST100(356, true),
	CHEST101(357, true),
	CHEST102(358, true),
	CHEST103(359, true),
	CHEST104(360, true),
	CHEST105(361, true),
	CHEST106(362, true),
	CHEST107(363, true),
	CHEST108(364, true),
	CHEST109(365, true),
	CHEST110(366, true),
	CHEST111(367, true),
	CHEST112(368, true),
	CHEST113(369, true),
	CHEST114(370, true),
	CHEST115(371, true),
	CHEST116(372, true),
	CHEST117(373, true),
	CHEST118(374, true),
	CHEST119(375, true),
	CHEST120(376, true),
	CHEST121(377, true),
	CHEST122(378, true),
	CHEST123(379, true),
	CHEST124(380, true),
	CHEST125(381, true),
	CHEST126(382, true),
	CHEST127(383, true),
	CHEST128(384, true),;

	private final int id;
	private boolean solid = false;

	private String musicName;

	private MusicType musicType;

	private TileType(int id) {
		this.id = id;
	}

	private TileType(int id, boolean solid) {
		this.id = id;
		this.solid = solid;
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
