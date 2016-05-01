package rest;

	public enum Happenings implements HappeningDetails {
		SPPED(1, 0.005), GROW(2, 0.005), PISTOL(3, 0.002), SHOTGUN(4, 0.001);

		private final int type;
		private final int width;
		private final int heigth;
		private final int subType;
		private final String borders;
		
		private final double chance;


		Happenings(int subType, double chance) {
			width = 10;
			heigth = 10;
			borders = "M0,0 L0,10 10,10 10,0 Z";
			type = Constants.BONUS;
			this.subType = subType;
			this.chance =chance;

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