package Model;

public class ActionModel {
	private int a,b,kq;

	public ActionModel(int a, int b, int kq) {
		super();
		this.a = a;
		this.b = b;
		this.kq = kq;
	}

	public ActionModel() {
		this.a = 0;
		this.b = 0;
		this.kq = 0;
		super();
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getKq() {
		return kq;
	}

	public void setKq(int kq) {
		this.kq = kq;
	}
}
