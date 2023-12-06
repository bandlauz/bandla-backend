package uz.bandla.telegrambot.passport.decrypt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class Aes256Cbc {
    private final Cbc cbc;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    public Aes256Cbc(byte[] key, byte[] iv) {
        cbc = new Cbc(iv, key, outputStream);
    }

    public byte[] decrypt(byte[] data) throws Exception {
        cbc.decrypt(data);
        cbc.finishDecryption();
        return outputStream.toByteArray();
    }

    static class Cbc {
        private final Aes256 _cipher;
        private final byte[] _current;
        private byte[] _buffer = null;
        private final byte[] _tmp;
        private byte[] _outBuffer = null;
        private boolean _outBufferUsed = false;
        private final byte[] _overflow;
        private int _overflowUsed;
        private final OutputStream _output;

        public Cbc(byte[] iv, byte[] key, OutputStream output) {
            _cipher = new Aes256(key);
            _current = new byte[16];
            System.arraycopy(iv, 0, _current, 0, 16);
            _tmp = new byte[16];
            _buffer = new byte[16];
            _outBuffer = new byte[16];
            _outBufferUsed = false;
            _overflow = new byte[16];
            _overflowUsed = 0;
            _output = output;
        }

        private void decryptBlock(byte[] inBuffer) {
            System.arraycopy(inBuffer, 0, _buffer, 0, 16);
            _cipher.decrypt(_buffer, 0, _tmp, 0);

            for (int i = 0; i < 16; ++i) {
                _tmp[i] ^= _current[i];
                _current[i] = _buffer[i];
                _outBuffer[i] = _tmp[i];
            }

        }

        public void decrypt(byte[] data) throws IOException {
            if (data != null) {
                decrypt(data, data.length);
            }

        }

        public void decrypt(byte[] data, int length) throws IOException {
            if (data != null && length > 0) {
                for (int i = 0; i < length; ++i) {
                    _overflow[_overflowUsed++] = data[i];
                    if (_overflowUsed == 16) {
                        if (_outBufferUsed) {
                            _output.write(_outBuffer);
                        }

                        decryptBlock(_overflow);
                        _outBufferUsed = true;
                        _overflowUsed = 0;
                    }
                }

            }
        }

        public void finishDecryption() throws IOException {
            if (_outBufferUsed) {
                _output.write(_outBuffer, 0, 16);
                _output.close();
            }
        }
    }

    static final class Aes256 {
        private final byte[] _expandedKey = new byte[240];
        private final byte[] _tmp = new byte[16];
        private final byte[] _sBox = new byte[]{99, 124, 119, 123, -14, 107, 111, -59, 48, 1, 103, 43, -2, -41, -85, 118, -54, -126, -55, 125, -6, 89, 71, -16, -83, -44, -94, -81, -100, -92, 114, -64, -73, -3, -109, 38, 54, 63, -9, -52, 52, -91, -27, -15, 113, -40, 49, 21, 4, -57, 35, -61, 24, -106, 5, -102, 7, 18, -128, -30, -21, 39, -78, 117, 9, -125, 44, 26, 27, 110, 90, -96, 82, 59, -42, -77, 41, -29, 47, -124, 83, -47, 0, -19, 32, -4, -79, 91, 106, -53, -66, 57, 74, 76, 88, -49, -48, -17, -86, -5, 67, 77, 51, -123, 69, -7, 2, 127, 80, 60, -97, -88, 81, -93, 64, -113, -110, -99, 56, -11, -68, -74, -38, 33, 16, -1, -13, -46, -51, 12, 19, -20, 95, -105, 68, 23, -60, -89, 126, 61, 100, 93, 25, 115, 96, -127, 79, -36, 34, 42, -112, -120, 70, -18, -72, 20, -34, 94, 11, -37, -32, 50, 58, 10, 73, 6, 36, 92, -62, -45, -84, 98, -111, -107, -28, 121, -25, -56, 55, 109, -115, -43, 78, -87, 108, 86, -12, -22, 101, 122, -82, 8, -70, 120, 37, 46, 28, -90, -76, -58, -24, -35, 116, 31, 75, -67, -117, -118, 112, 62, -75, 102, 72, 3, -10, 14, 97, 53, 87, -71, -122, -63, 29, -98, -31, -8, -104, 17, 105, -39, -114, -108, -101, 30, -121, -23, -50, 85, 40, -33, -116, -95, -119, 13, -65, -26, 66, 104, 65, -103, 45, 15, -80, 84, -69, 22};
        private final byte[] _invSBox = new byte[]{82, 9, 106, -43, 48, 54, -91, 56, -65, 64, -93, -98, -127, -13, -41, -5, 124, -29, 57, -126, -101, 47, -1, -121, 52, -114, 67, 68, -60, -34, -23, -53, 84, 123, -108, 50, -90, -62, 35, 61, -18, 76, -107, 11, 66, -6, -61, 78, 8, 46, -95, 102, 40, -39, 36, -78, 118, 91, -94, 73, 109, -117, -47, 37, 114, -8, -10, 100, -122, 104, -104, 22, -44, -92, 92, -52, 93, 101, -74, -110, 108, 112, 72, 80, -3, -19, -71, -38, 94, 21, 70, 87, -89, -115, -99, -124, -112, -40, -85, 0, -116, -68, -45, 10, -9, -28, 88, 5, -72, -77, 69, 6, -48, 44, 30, -113, -54, 63, 15, 2, -63, -81, -67, 3, 1, 19, -118, 107, 58, -111, 17, 65, 79, 103, -36, -22, -105, -14, -49, -50, -16, -76, -26, 115, -106, -84, 116, 34, -25, -83, 53, -123, -30, -7, 55, -24, 28, 117, -33, 110, 71, -15, 26, 113, 29, 41, -59, -119, 111, -73, 98, 14, -86, 24, -66, 27, -4, 86, 62, 75, -58, -46, 121, 32, -102, -37, -64, -2, 120, -51, 90, -12, 31, -35, -88, 51, -120, 7, -57, 49, -79, 18, 16, 89, 39, -128, -20, 95, 96, 81, 127, -87, 25, -75, 74, 13, 45, -27, 122, -97, -109, -55, -100, -17, -96, -32, 59, 77, -82, 42, -11, -80, -56, -21, -69, 60, -125, 83, -103, 97, 23, 43, 4, 126, -70, 119, -42, 38, -31, 105, 20, 99, 85, 33, 12, 125};

        private void substituteWord(byte[] value) {
            for (int i = 0; i < 4; ++i) {
                value[i] = _sBox[value[i] & 255];
            }
        }

        private byte[] rotate(byte[] value) {
            byte tmp = value[0];

            for (int i = 1; i < 4; ++i) {
                value[i - 1] = value[i];
            }

            value[3] = tmp;
            return value;
        }

        public Aes256(byte[] key) {
            System.arraycopy(key, 0, _expandedKey, 0, 32);

            for (int i = 32; i < 240; i += 4) {
                System.arraycopy(_expandedKey, i - 4, _tmp, 0, 4);
                if (i % 32 == 0) {
                    substituteWord(rotate(_tmp));
                    byte[] var10000 = _tmp;
                    var10000[0] = (byte) (var10000[0] ^ 1 << i / 32 - 1);
                } else if (i % 32 == 16) {
                    substituteWord(_tmp);
                }

                for (int j = 0; j < 4; ++j) {
                    _expandedKey[i + j] = (byte) (_expandedKey[i - 32 + j] ^ _tmp[j]);
                }
            }

        }

        private void addRoundKey(int index) {
            for (int i = 0; i < 16; ++i) {
                _tmp[i] ^= _expandedKey[index + i];
            }

        }

        private int times2(int b) {
            int result = b << 1;
            if ((b & 128) != 0) {
                result ^= 27;
            }

            return result & 255;
        }

        private byte mul(int a, byte b) {
            int result = 0;
            int first = a;

            for (int current = b & 255; first != 0; current = times2(current)) {
                if ((first & 1) != 0) {
                    result ^= current;
                }

                first >>= 1;
            }

            return (byte) (result & 255);
        }

        private void invShiftRows() {
            byte tmp = _tmp[13];
            _tmp[13] = _tmp[9];
            _tmp[9] = _tmp[5];
            _tmp[5] = _tmp[1];
            _tmp[1] = tmp;
            tmp = _tmp[2];
            _tmp[2] = _tmp[10];
            _tmp[10] = tmp;
            tmp = _tmp[6];
            _tmp[6] = _tmp[14];
            _tmp[14] = tmp;
            tmp = _tmp[3];
            _tmp[3] = _tmp[7];
            _tmp[7] = _tmp[11];
            _tmp[11] = _tmp[15];
            _tmp[15] = tmp;
        }

        private void invSubstituteState() {
            for (int i = 0; i < 16; ++i) {
                _tmp[i] = _invSBox[_tmp[i] & 255];
            }

        }

        private void invMixColumn(int index) {
            int s0 = mul(14, _tmp[index]) ^ mul(11, _tmp[index + 1]) ^ mul(13, _tmp[index + 2]) ^ mul(9, _tmp[index + 3]);
            int s1 = mul(9, _tmp[index]) ^ mul(14, _tmp[index + 1]) ^ mul(11, _tmp[index + 2]) ^ mul(13, _tmp[index + 3]);
            int s2 = mul(13, _tmp[index]) ^ mul(9, _tmp[index + 1]) ^ mul(14, _tmp[index + 2]) ^ mul(11, _tmp[index + 3]);
            int s3 = mul(11, _tmp[index]) ^ mul(13, _tmp[index + 1]) ^ mul(9, _tmp[index + 2]) ^ mul(14, _tmp[index + 3]);
            _tmp[index] = (byte) (s0 & 255);
            _tmp[index + 1] = (byte) (s1 & 255);
            _tmp[index + 2] = (byte) (s2 & 255);
            _tmp[index + 3] = (byte) (s3 & 255);
        }

        private void invMixColumns() {
            invMixColumn(0);
            invMixColumn(4);
            invMixColumn(8);
            invMixColumn(12);
        }

        public void decrypt(byte[] inBlock, int inIndex, byte[] outBlock, int outIndex) {
            System.arraycopy(inBlock, inIndex, _tmp, 0, 16);
            addRoundKey(224);

            for (int round = 13; round > 0; --round) {
                invShiftRows();
                invSubstituteState();
                addRoundKey(round * 16);
                invMixColumns();
            }

            invShiftRows();
            invSubstituteState();
            addRoundKey(0);
            System.arraycopy(_tmp, 0, outBlock, outIndex, 16);
        }
    }
}
