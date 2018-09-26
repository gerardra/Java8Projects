package au.com.fraudulentchecker.converters;

public interface Converter <S, S1, T>{
	
	T convert(final S line, final S1 separator);
	
}
