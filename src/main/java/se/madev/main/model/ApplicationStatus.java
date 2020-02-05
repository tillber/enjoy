package se.madev.main.model;

public enum ApplicationStatus {
	APPLIED {
		public String toString() {
			return "APPLIED";
		}
	},
	ACCEPTED {
		public String toString() {
			return "ACCEPTED";
		}
	},
	REJECTED {
		public String toString() {
			return "REJECTED";
		}
	}
}
