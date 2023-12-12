package uz.bandla.telegrambot.passport.decrypt;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateCrtKeySpec;

public class Rsa {
    Rsa() {
    }

    public static byte[] decrypt(String privateKey, byte[] secret) throws Exception {
        String pkcs8Pem = privateKey.replace("-----BEGIN RSA PRIVATE KEY-----", "");
        pkcs8Pem = pkcs8Pem.replace("-----END RSA PRIVATE KEY-----", "");
        pkcs8Pem = pkcs8Pem.replaceAll("\\s+", "");
        byte[] pkcs8EncodedBytes = Base64.decode(pkcs8Pem, 0);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privKey = kf.generatePrivate(getRSAKeySpec(pkcs8EncodedBytes));
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-1AndMGF1Padding");
        cipher.init(2, privKey);
        return cipher.doFinal(secret);
    }

    private static RSAPrivateCrtKeySpec getRSAKeySpec(byte[] keyBytes) throws IOException {
        DerParser parser = new DerParser(keyBytes);
        Asn1Object sequence = parser.read();
        if (sequence.getType() != 16) {
            throw new IOException("Invalid DER: not a sequence");
        } else {
            parser = sequence.getParser();
            parser.read();
            BigInteger modulus = parser.read().getInteger();
            BigInteger publicExp = parser.read().getInteger();
            BigInteger privateExp = parser.read().getInteger();
            BigInteger prime1 = parser.read().getInteger();
            BigInteger prime2 = parser.read().getInteger();
            BigInteger exp1 = parser.read().getInteger();
            BigInteger exp2 = parser.read().getInteger();
            BigInteger crtCoef = parser.read().getInteger();
            return new RSAPrivateCrtKeySpec(modulus, publicExp, privateExp, prime1, prime2, exp1, exp2, crtCoef);
        }
    }

    private static class DerParser {
        protected InputStream in;

        public DerParser(InputStream in) {
            this.in = in;
        }

        public DerParser(byte[] bytes) {
            this(new ByteArrayInputStream(bytes));
        }

        public Asn1Object read() throws IOException {
            int tag = this.in.read();
            if (tag == -1) {
                throw new IOException("Invalid DER: stream too short, missing tag");
            } else {
                int length = this.getLength();
                byte[] value = new byte[length];
                int n = this.in.read(value);
                if (n < length) {
                    throw new IOException("Invalid DER: stream too short, missing value");
                } else {
                    return new Asn1Object(tag, length, value);
                }
            }
        }

        private int getLength() throws IOException {
            int i = this.in.read();
            if (i == -1) {
                throw new IOException("Invalid DER: length missing");
            } else if ((i & -128) == 0) {
                return i;
            } else {
                int num = i & 127;
                if (i < 255 && num <= 4) {
                    byte[] bytes = new byte[num];
                    int n = this.in.read(bytes);
                    if (n < num) {
                        throw new IOException("Invalid DER: length too short");
                    } else {
                        return (new BigInteger(1, bytes)).intValue();
                    }
                } else {
                    throw new IOException("Invalid DER: length field too big (" + i + ")");
                }
            }
        }
    }

    private static class Asn1Object {
        protected final int type;
        protected final int length;
        protected final byte[] value;
        protected final int tag;

        public Asn1Object(int tag, int length, byte[] value) {
            this.tag = tag;
            this.type = tag & 31;
            this.length = length;
            this.value = value;
        }

        public int getType() {
            return this.type;
        }

        public int getLength() {
            return this.length;
        }

        public byte[] getValue() {
            return this.value;
        }

        public boolean isConstructed() {
            return (this.tag & 32) == 32;
        }

        public DerParser getParser() throws IOException {
            if (!this.isConstructed()) {
                throw new IOException("Invalid DER: can't parse primitive entity");
            } else {
                return new DerParser(this.value);
            }
        }

        public BigInteger getInteger() throws IOException {
            if (this.type != 2) {
                throw new IOException("Invalid DER: object is not integer");
            } else {
                return new BigInteger(this.value);
            }
        }
    }
}
