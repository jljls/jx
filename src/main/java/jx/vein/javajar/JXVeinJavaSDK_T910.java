package jx.vein.javajar;

public class JXVeinJavaSDK_T910 {
	public static final int veinSampleSize = 166572;
	public static final int veinFeatureSize = 1344;
	
	public static final float veinMisrateThresh = 0.33f;
	



	private native int CheckVeinSampleQuality(byte[] sample_buf);
	private native int GrabVeinFeature(byte[] sample_buf, byte[] feat_buf);
	private native int VericateTwoVeinFeature(byte[] feat_buf1, byte[] feat_buf2);
	private native float CalcFeatMisrate(byte[] feat_buf1, byte[] feat_buf2);
	
	
	
	public int jxCheckVeinSampleQuality(byte[] sample_buf) throws Exception
	{
		if(sample_buf.length != veinSampleSize)
			throw new Exception("Invalid buf length");
		int ret = CheckVeinSampleQuality(sample_buf);
		return ret;
	}
	
	public int jxGrabVeinFeature(byte[] sample_buf, byte[] feat_buf) throws Exception
	{
		if(sample_buf.length != veinSampleSize || feat_buf.length != veinFeatureSize)
			throw new Exception("Invalid buf length");
		int ret = GrabVeinFeature(sample_buf, feat_buf);
		return ret;
	}
	
	public int jxVericateTwoVeinFeature(byte[] feat_buf1, byte[] feat_buf2) throws Exception
	{
		if(feat_buf1.length != veinFeatureSize || feat_buf2.length != veinFeatureSize)
			throw new Exception("Invalid buf length");
		int ret = VericateTwoVeinFeature(feat_buf1, feat_buf2);
		return ret;
	}
	
	public float jxCalcFeatMisrate(byte[] feat_buf1, byte[] feat_buf2) throws Exception
	{
		if(feat_buf1.length != veinFeatureSize || feat_buf2.length != veinFeatureSize)
			throw new Exception("Invalid buf length");
		float score = CalcFeatMisrate(feat_buf1, feat_buf2);
		return score;
	}
}