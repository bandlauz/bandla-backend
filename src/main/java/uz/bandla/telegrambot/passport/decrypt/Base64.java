package uz.bandla.telegrambot.passport.decrypt;

public class Base64 {

    public static byte[] decode(String str, int flags) {
        return decode(str.getBytes(), flags);
    }

    public static byte[] decode(byte[] input, int flags) {
        return decode(input, 0, input.length, flags);
    }

    public static byte[] decode(byte[] input, int offset, int len, int flags) {
        Decoder decoder = new Decoder(flags, new byte[len * 3 / 4]);
        if (!decoder.process(input, offset, len, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (decoder.op == decoder.output.length) {
            return decoder.output;
        } else {
            byte[] temp = new byte[decoder.op];
            System.arraycopy(decoder.output, 0, temp, 0, decoder.op);
            return temp;
        }
    }

    private Base64() {
    }

    static class Decoder extends Coder {
        private static final int[] DECODE = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int[] DECODE_WEBSAFE = new int[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private int state;
        private int value;
        private final int[] alphabet;

        public Decoder(int flags, byte[] output) {
            this.output = output;
            this.alphabet = (flags & 8) == 0 ? DECODE : DECODE_WEBSAFE;
            this.state = 0;
            this.value = 0;
        }

        @Override
        public boolean process(byte[] input, int offset, int len, boolean finish) {
            if (this.state == 6) {
                return false;
            } else {
                int p = offset;
                len += offset;
                int state = this.state;
                int value = this.value;
                int op = 0;
                byte[] output = this.output;
                int[] alphabet = this.alphabet;

                while (p < len) {
                    if (state == 0) {
                        while (p + 4 <= len && (value = alphabet[input[p] & 255] << 18 | alphabet[input[p + 1] & 255] << 12 | alphabet[input[p + 2] & 255] << 6 | alphabet[input[p + 3] & 255]) >= 0) {
                            output[op + 2] = (byte) value;
                            output[op + 1] = (byte) (value >> 8);
                            output[op] = (byte) (value >> 16);
                            op += 3;
                            p += 4;
                        }

                        if (p >= len) {
                            break;
                        }
                    }

                    int d = alphabet[input[p++] & 255];
                    switch (state) {
                        case 0 -> {
                            if (d >= 0) {
                                value = d;
                                ++state;
                            } else if (d != -1) {
                                this.state = 6;
                                return false;
                            }
                        }
                        case 1 -> {
                            if (d >= 0) {
                                value = value << 6 | d;
                                ++state;
                            } else if (d != -1) {
                                this.state = 6;
                                return false;
                            }
                        }
                        case 2 -> {
                            if (d >= 0) {
                                value = value << 6 | d;
                                ++state;
                            } else if (d == -2) {
                                output[op++] = (byte) (value >> 4);
                                state = 4;
                            } else if (d != -1) {
                                this.state = 6;
                                return false;
                            }
                        }
                        case 3 -> {
                            if (d >= 0) {
                                value = value << 6 | d;
                                output[op + 2] = (byte) value;
                                output[op + 1] = (byte) (value >> 8);
                                output[op] = (byte) (value >> 16);
                                op += 3;
                                state = 0;
                            } else if (d == -2) {
                                output[op + 1] = (byte) (value >> 2);
                                output[op] = (byte) (value >> 10);
                                op += 2;
                                state = 5;
                            } else if (d != -1) {
                                this.state = 6;
                                return false;
                            }
                        }
                        case 4 -> {
                            if (d == -2) {
                                ++state;
                            } else if (d != -1) {
                                this.state = 6;
                                return false;
                            }
                        }
                        case 5 -> {
                            if (d != -1) {
                                this.state = 6;
                                return false;
                            }
                        }
                    }
                }

                if (!finish) {
                    this.state = state;
                    this.value = value;
                } else {
                    switch (state) {
                        default -> {
                        }
                        case 1, 4 -> {
                            this.state = 6;
                            return false;
                        }
                        case 2 -> output[op++] = (byte) (value >> 4);
                        case 3 -> {
                            output[op++] = (byte) (value >> 10);
                            output[op++] = (byte) (value >> 2);
                        }
                    }

                    this.state = state;
                }
                this.op = op;
                return true;
            }
        }
    }

    abstract static class Coder {
        public byte[] output;
        public int op;

        Coder() {
        }

        public abstract boolean process(byte[] var1, int var2, int var3, boolean var4);
    }
}
