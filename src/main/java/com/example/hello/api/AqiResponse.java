package com.example.hello.api;

public class AqiResponse {
	
	private Data data;
	
	public AqiResponse() {
	}

	public AqiResponse(Data data) {
		this.data = data;
	}


	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}
	
	@Override
	public String toString() {
		return "AqiResponse [data=" + data + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AqiResponse other = (AqiResponse) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		return true;
	}


}
