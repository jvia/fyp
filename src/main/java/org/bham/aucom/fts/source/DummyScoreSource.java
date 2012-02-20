package org.bham.aucom.fts.source;

import org.bham.aucom.data.Score;

public class DummyScoreSource extends AucomSourceAdapter<Score> {
	private static final long serialVersionUID = 0L;

	public DummyScoreSource() {
		super("DummyScoreSource");
	}

	@Override
	protected Score iNextItem() throws Exception {
		return Score.createRandomRangeScore();
	}

	@Override
	protected void iDisconnect() throws ActionFailedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void iConnect() throws ActionFailedException {
		// is ignored by this implementation
	}

}
