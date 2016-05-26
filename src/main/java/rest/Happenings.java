package rest;

	public enum Happenings implements HappeningDetails {
		SPPED(1, 0.005, 35, 40, "M0,0 L0,35 40,35 40,0 Z"), GROW(2, 0.005, 30, 40, "M0,0 L0,30 40,30 40,0 Z"), PISTOL(3, 0.002, 31, 40, "M0,0 L0,31 40,31 40,0 Z"), SHOTGUN(4, 0.001, 40 , 22, "M0,0 L0,40 22,40 22,0 Z");

		private final int type;
		private final int width;
		private final int heigth;
		private final int subType;
		private final String borders;
		
		private final double chance;


		Happenings(int subType, double chance, int width, int heigth, String borders) {
			//borders = "M0,0 L0,10 10,10 10,0 Z";
			type = Constants.BONUS;
			this.subType = subType;
			this.chance =chance;
			this.width = width;
			this.heigth = heigth;
			this.borders = borders;

		}

		public int type() {
			return type;
		}

		public int width() {
			return width;
		}

		public int height() {
			return heigth;
		}

		@Override
		public int subType() {
			return subType;
		}


		@Override
		public Forms form() {

			return Forms.HALF_CIRCKLE;
		}


		@Override
		public String borders() {
			return borders;
		}
		@Override
		public double chance() {
			return chance;
		}

}