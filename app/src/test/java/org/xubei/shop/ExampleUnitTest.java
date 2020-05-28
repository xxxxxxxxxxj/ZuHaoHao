package org.xubei.shop;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import io.reactivex.Flowable;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
//
//    private String str;
//
//    private Integer intA;
//
//    private int intB;

    @Test
    public void testRxJavaTest() {
        double d = 123.456;
        long l = Double.doubleToRawLongBits(d);
        System.out.println(l);

        //  System.out.print("str="+str +"\nintA="+intA+"\nintB="+intB);
    }

    //        Observable.range(0, 3).flatMap(new Function<Integer, ObservableSource<?>>() {
//            @Override
//            public ObservableSource<?> apply(Integer integer) throws Exception {
//                System.out.println("apply->" + integer);
//                return Observable.range(0, 3);
//            }
//        }).subscribe(new Consumer<Object>() {
//                         @Override
//                         public void accept(Object o) throws Exception {
//                             System.out.println("accept->" + o);
//                         }
//                     }
//
//        );
    @Test
    public void testRxJavaTest1() {
//        Observable.fromArray(1, 2, 3).subscribe(new Observer<Integer>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//                System.out.println("onSubscribe");
//            }
//
//            @Override
//            public void onNext(Integer value) {
//                System.out.println("onNext->" + value);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                System.out.println("onError->" + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                System.out.println("onComplete->");
//            }
//        });
//        String md52 = EncryptUtils.encryptMD5ToString("http://www.baidu.com");
    }

    @Test
    public void testRxJavaTest2() {
        Flowable.fromArray(1, 2, 3, 4, 5, 6).subscribe(new Subscriber<Integer>() {

            Subscription subscription;

            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("onSubscribe");
                subscription = s;
                subscription.request(1);
            }

            @Override
            public void onNext(Integer integer) {
                System.out.println("onNext->" + integer);
                subscription.request(1);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("onError");
            }

            @Override
            public void onComplete() {
                System.out.println("onComplete");
            }
        });
    }

    @Test
    public void testRandom() {
        for (int i = 1; i <= 100; i++) {
            if (i % 10 == 0) {
                System.out.println((int) (Math.random() * 48));
            } else {
                System.out.print((int) (Math.random() * 48) + " - ");
            }

        }
    }

    @Test
    public void test16toHash() {
        //   String str=  ConvertUtils.bytes2HexString(getBaiduTestBytes());
        //0x1b, 0x40, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x11, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, 0x1b, 0x45, 0x01, 0x1b, 0x47, 0x01,
        // 0x0a, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x00, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00
//        try {
//            System.out.print(new String(testByte, "gbk"));
//        } catch (UnsupportedEncodingException e) {10110000 11011001
//            e.printStackTrace();
//        }
   //     System.out.print(a + " - " + testByte[0] + testByte[1]);

    }

  //  int a = 0xa3ba;


   // byte[] testByte = new byte[]{(byte) 0xa3, (byte) 0xba};


    //百度小票
//    public static byte[] getBaiduTestBytes() {
//        byte[] rv = new byte[]{
//                0x1b, 0x40, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x11, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, 0x1b, 0x45, 0x01, 0x1b, 0x47, 0x01, (byte) 0xb1, (byte) 0xbe
//                , (byte) 0xb5, (byte) 0xea, (byte) 0xc1, (byte) 0xf4, (byte) 0xb4, (byte) 0xe6, 0x0a, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x00, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, 0x2a, 0x2a, 0x2a
//                , 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a
//                , 0x2a, 0x0a
//                , 0x1b, 0x40, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x11, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, 0x1b, 0x45, 0x01, 0x1b, 0x47, 0x01, 0x1b, 0x61
//                , 0x01, 0x23, 0x31, 0x35, 0x20, (byte) 0xb0, (byte) 0xd9, (byte) 0xb6, (byte) 0xc8, (byte) 0xcd, (byte) 0xe2, (byte) 0xc2, (byte) 0xf4, 0x0a, 0x5b, (byte) 0xbb, (byte) 0xf5, (byte) 0xb5, (byte) 0xbd, (byte) 0xb8, (byte) 0xb6, (byte) 0xbf, (byte) 0xee, 0x5d, 0x0a, 0x1b, 0x4d, 0x00
//                , 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x00, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a
//                , 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x0a
//                , 0x1b, 0x40, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x01, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, (byte) 0xc6, (byte) 0xda, (byte) 0xcd, (byte) 0xfb, (byte) 0xcb, (byte) 0xcd, (byte) 0xb4, (byte) 0xef
//                , (byte) 0xca, (byte) 0xb1, (byte) 0xbc, (byte) 0xe4, (byte) 0xa3, (byte) 0xba, (byte) 0xc1, (byte) 0xa2, (byte) 0xbc, (byte) 0xb4, (byte) 0xc5, (byte) 0xe4, (byte) 0xcb, (byte) 0xcd, 0x0a, (byte) 0xb6, (byte) 0xa9, (byte) 0xb5, (byte) 0xa5, (byte) 0xb1, (byte) 0xb8, (byte) 0xd7, (byte) 0xa2, (byte) 0xa3, (byte) 0xba, (byte) 0xc7, (byte) 0xeb, (byte) 0xcb
//                , (byte) 0xcd, (byte) 0xb5, (byte) 0xbd, (byte) 0xbf, (byte) 0xfc, (byte) 0xbf, (byte) 0xc6, (byte) 0xce, (byte) 0xf7, (byte) 0xc3, (byte) 0xc5, 0x2c, (byte) 0xb2, (byte) 0xbb, (byte) 0xd2, (byte) 0xaa, (byte) 0xc0, (byte) 0xb1, 0x0a, (byte) 0xb7, (byte) 0xa2, (byte) 0xc6, (byte) 0xb1, (byte) 0xd0, (byte) 0xc5, (byte) 0xcf, (byte) 0xa2, (byte) 0xa3
//                , (byte) 0xba, (byte) 0xb0, (byte) 0xd9, (byte) 0xb6, (byte) 0xc8, (byte) 0xcd, (byte) 0xe2, (byte) 0xc2, (byte) 0xf4, (byte) 0xb7, (byte) 0xa2, (byte) 0xc6, (byte) 0xb1, 0x0a, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x00, 0x1b, 0x45, 0x00, 0x1b, 0x47
//                , 0x00, 0x1b, 0x61, 0x00, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a
//                , 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x0a
//                , 0x1b, 0x40, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x00, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, (byte) 0xb6, (byte) 0xa9, (byte) 0xb5, (byte) 0xa5, (byte) 0xb1, (byte) 0xe0, (byte) 0xba, (byte) 0xc5
//                , (byte) 0xa3, (byte) 0xba, 0x31, 0x34, 0x31, 0x38, 0x37, 0x31, 0x38, 0x36, 0x39, 0x31, 0x31, 0x36, 0x38, 0x39, 0x0a, (byte) 0xcf, (byte) 0xc2, (byte) 0xb5, (byte) 0xa5, (byte) 0xca, (byte) 0xb1, (byte) 0xbc, (byte) 0xe4, (byte) 0xa3, (byte) 0xba, 0x32
//                , 0x30, 0x31, 0x34, 0x2d, 0x31, 0x32, 0x2d, 0x31, 0x36, 0x20, 0x31, 0x36, 0x3a, 0x33, 0x31, 0x0a, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x00, 0x1b, 0x45, 0x00
//                , 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a
//                , 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x0a
//                , 0x1b, 0x40, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x01, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, (byte) 0xb2, (byte) 0xcb, (byte) 0xc6, (byte) 0xb7, (byte) 0xc3, (byte) 0xfb, (byte) 0xb3, (byte) 0xc6
//                , 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, 0x20, (byte) 0xca, (byte) 0xfd, (byte) 0xc1, (byte) 0xbf, 0x20, 0x20, 0x20, 0x20, 0x20, (byte) 0xbd, (byte) 0xf0, (byte) 0xb6, (byte) 0xee, 0x0a, 0x1b, 0x4d, 0x00, 0x1b, 0x61
//                , 0x00, 0x1d, 0x21, 0x00, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d
//                , 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x0a, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x01, 0x1b
//                , 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, (byte) 0xcf, (byte) 0xe3, (byte) 0xc0, (byte) 0xb1, (byte) 0xc3, (byte) 0xe6, (byte) 0xcc, (byte) 0xd7, (byte) 0xb2, (byte) 0xcd, 0x1b, 0x24, (byte) 0xf2, 0x00, 0x31, 0x1b, 0x24, 0x25, 0x01, (byte) 0xa3
//                , (byte) 0xa4, 0x34, 0x30, 0x2e, 0x30, 0x30, 0x0a, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x00, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, 0x1b, 0x4d, 0x00
//                , 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x00, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x01, 0x1b, 0x45, 0x00, 0x1b
//                , 0x47, 0x00, 0x1b, 0x61, 0x00, (byte) 0xcb, (byte) 0xd8, (byte) 0xca, (byte) 0xb3, (byte) 0xcc, (byte) 0xec, (byte) 0xcf, (byte) 0xc2, (byte) 0xba, (byte) 0xba, (byte) 0xb1, (byte) 0xa4, 0x1b, 0x24, (byte) 0xf2, 0x00, 0x31, 0x1b, 0x24, 0x25, 0x01, (byte) 0xa3, (byte) 0xa4
//                , 0x33, 0x38, 0x2e, 0x30, 0x30, 0x0a, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x00, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, 0x1b, 0x4d, 0x00, 0x1b
//                , 0x61, 0x00, 0x1d, 0x21, 0x00, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00
//                , 0x1b, 0x40, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x00, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d
//                , 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x2d, 0x0a
//                , 0x1b, 0x40, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x00, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a
//                , 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x0a, 0x1b, 0x4d, 0x00
//                , 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x01, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, (byte) 0xd0, (byte) 0xd5, (byte) 0xc3, (byte) 0xfb, (byte) 0xa3, (byte) 0xba, (byte) 0xb0, (byte) 0xd9, (byte) 0xb6, (byte) 0xc8, (byte) 0xb2, (byte) 0xe2, (byte) 0xca
//                , (byte) 0xd4, 0x0a, (byte) 0xb5, (byte) 0xd8, (byte) 0xd6, (byte) 0xb7, (byte) 0xa3, (byte) 0xba, (byte) 0xbf, (byte) 0xfc, (byte) 0xbf, (byte) 0xc6, (byte) 0xbf, (byte) 0xc6, (byte) 0xbc, (byte) 0xbc, (byte) 0xb4, (byte) 0xf3, (byte) 0xcf, (byte) 0xc3, 0x0a, (byte) 0xb5, (byte) 0xe7, (byte) 0xbb, (byte) 0xb0, (byte) 0xa3, (byte) 0xba, 0x31
//                , 0x38, 0x37, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x0a
//                , 0x1b, 0x40, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x00, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a
//                , 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x0a, (byte) 0xb0, (byte) 0xd9, (byte) 0xb6
//                , (byte) 0xc8, (byte) 0xb2, (byte) 0xe2, (byte) 0xca, (byte) 0xd4, (byte) 0xc9, (byte) 0xcc, (byte) 0xbb, (byte) 0xa7, 0x0a, 0x31, 0x38, 0x37, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x30, 0x0a, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d
//                , 0x21, 0x00, 0x1b, 0x45, 0x00, 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a
//                , 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x2a, 0x0a, 0x1b, 0x4d, 0x00, 0x1b, 0x61, 0x00, 0x1d, 0x21, 0x00, 0x1b, 0x45, 0x00
//                , 0x1b, 0x47, 0x00, 0x1b, 0x61, 0x00, 0x1b, 0x61, 0x01, 0x23, 0x31, 0x35, 0x20, (byte) 0xb0, (byte) 0xd9, (byte) 0xb6, (byte) 0xc8, (byte) 0xcd, (byte) 0xe2, (byte) 0xc2, (byte) 0xf4, 0x20, 0x20, 0x31, 0x31, (byte) 0xd4, (byte) 0xc2, 0x30
//                , 0x39, (byte) 0xc8, (byte) 0xd5, 0x20, 0x31, 0x37, 0x3a, 0x35, 0x30, 0x3a, 0x33, 0x30, 0x0a, 0x0a, 0x0a, 0x0a, 0x0a
//        };
//        return rv;
//    }

//
//    String testStr = "27,97,1,27,69,15,29,33,17,-80,-39,-73,-42,-80,-39,-56,-85,-74,-18,-51,-53,-53,-80,27,33,27,69,0,10,27,97,1,27,69,15,84,82,65,86,69,76,69,65,83,89,32,71,109,98,72,27,69,0,10,27,97,1,27,69,15,70,114,97,110,107,102,117,114,116,101,114,32,83,116,114,46,55,49,45,55,53,27,69,0,10,27,97,1,27,69,15,54,53,55,54,48,32,69,115,99,104,98,111,114,110,27,69,0,10,27,97,1,27,69,15,71,101,114,109,97,110,121,27,69,0,10,27,97,1,27,69,15,86,65,84,32,78,111,46,58,32,68,69,50,57,54,53,55,56,49,50,48,27,69,0,10,10,27,97,0,27,69,15,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,27,69,0,10,27,97,1,27,69,15,84,65,88,32,82,69,70,85,78,68,32,70,79,82,77,32,71,69,82,77,65,78,89,27,69,0,10,27,97,0,27,69,15,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,27,69,0,10,10,27,97,0,27,69,15,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,27,69,0,10,27,97,0,27,69,15,48,49,47,82,69,84,65,73,76,69,82,32,68,69,84,65,73,76,83,32,-55,-52,-75,-22,-48,-59,-49,-94,27,69,0,10,27,97,0,27,69,15,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,27,69,0,10,27,97,0,27,69,15,78,97,109,101,32,32,32,32,32,32,32,32,32,58,32,42,42,42,115,116,114,46,27,69,0,10,27,97,0,27,69,15,86,65,84,32,78,111,46,32,32,32,32,32,32,58,32,42,42,42,27,69,0,10,27,97,0,27,69,15,82,101,116,97,105,108,111,114,32,78,111,46,32,58,32,71,101,114,109,97,110,121,27,69,0,10,27,97,0,27,69,15,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,27,69,0,10,27,97,0,27,69,15,48,50,47,68,69,83,67,82,73,80,84,73,79,78,32,79,70,32,71,79,79,68,83,27,69,0,10,27,97,0,27,69,15,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,27,69,0,10,27,97,0,27,69,15,-55,-52,-58,-73,-48,-59,-49,-94,27,69,0,10,27,97,0,27,69,15,68,97,116,101,32,111,102,32,83,97,108,101,27,69,0,10,27,97,0,27,69,15,81,84,89,32,82,101,99,101,112,105,116,32,78,111,46,32,84,111,116,97,108,32,105,110,99,108,46,86,65,84,27,69,0,10,10,10,10,27,97,0,27,69,15,73,110,118,111,105,99,101,115,32,105,110,99,108,46,86,65,84,32,49,57,37,58,27,69,0,10,27,97,0,27,69,15,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,32,55,37,58,27,69,0,10,27,97,0,27,69,15,84,111,116,97,108,32,115,97,108,101,115,32,97,109,111,117,110,116,27,69,0,10,27,97,0,27,69,15,-70,-84,-53,-80,-41,-36,-74,-18,-93,-70,27,69,0,10,27,97,0,27,69,15,82,101,102,117,110,100,32,97,109,111,117,110,116,27,69,0,10,27,97,0,27,69,15,-51,-53,-53,-80,-41,-36,-74,-18,-93,-70,27,69,0,10,27,97,0,27,69,15,68,65,84,69,-93,-84,82,69,84,65,73,76,69,82,32,83,73,71,78,65,84,85,82,69,27,69,0,10,27,97,0,27,69,15,-56,-43,-58,-38,-93,-84,-55,-52,-75,-22,-57,-87,-41,-42,27,69,0,10,10,27,97,0,27,69,15,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,27,69,0,10,27,97,0,27,69,15,78,111,116,32,116,114,97,110,115,102,101,114,97,98,108,101,46,65,112,112,108,105,99,97,98,108,101,32,111,110,108,121,32,116,111,32,99,105,116,105,122,101,110,32,119,105,116,104,32,112,101,114,109,97,110,101,110,116,32,112,108,97,99,101,32,111,102,32,114,101,115,105,100,101,110,99,101,32,111,117,116,115,105,100,101,32,116,104,101,32,69,85,46,69,120,112,111,114,116,32,109,117,115,116,32,116,97,107,101,32,112,108,97,99,101,32,119,105,116,104,105,110,32,51,32,109,111,110,116,104,115,46,27,69,0,10,10,27,97,0,27,69,15,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,27,69,0,10,27,97,0,27,69,15,48,51,47,67,85,83,84,79,77,69,82,32,68,69,84,65,73,76,83,32,-71,-53,-65,-51,-48,-59,-49,-94,27,69,0,10,27,97,0,27,69,15,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,27,69,0,10,27,97,0,27,69,15,80,65,83,83,80,79,82,84,32,78,79,46,10,-69,-92,-43,-43,-79,-32,-62,-21,27,69,0,10,27,97,0,27,69,15,78,65,84,73,79,78,65,76,73,84,89,10,-71,-6,-68,-82,-93,-70,27,69,0,10,27,97,0,27,69,15,76,65,83,84,32,78,65,77,69,10,-48,-43,-93,-88,-58,-76,-46,-12,-93,-87,-93,-70,27,69,0,10,27,97,0,27,69,15,70,73,82,83,84,32,78,65,77,69,10,-61,-5,-93,-88,-58,-76,-46,-12,-93,-87,-93,-70,27,69,0,10,27,97,0,27,69,15,80,69,82,77,65,78,69,78,84,32,65,68,68,82,69,83,83,10,-66,-45,-41,-95,-75,-40,-42,-73,-93,-70,27,69,0,10,27,97,0,27,69,15,67,79,85,78,84,82,89,10,-71,-6,-68,-46,-93,-70,27,69,0,10,27,97,0,27,69,15,69,77,65,73,76,32,65,68,68,82,69,83,83,10,-45,-54,-49,-28,-93,-70,27,69,0,10,27,97,0,27,69,15,77,79,66,73,76,69,32,78,79,46,10,-54,-42,-69,-6,-70,-59,-62,-21,-93,-70,27,69,0,10,27,97,0,27,69,15,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,27,69,0,10,27,97,0,27,69,15,48,52,47,76,69,71,65,76,32,68,69,67,76,65,82,65,84,73,79,78,10,27,69,0,10,27,97,0,27,69,15,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,27,69,0,10,27,97,0,27,69,15,66,121,32,115,105,110,103,105,110,103,32,97,110,100,32,115,117,98,109,105,116,116,105,110,103,32,116,104,105,115,32,102,111,114,109,32,116,111,32,84,97,120,32,70,114,101,101,32,69,97,115,121,32,102,111,114,32,112,97,121,109,101,110,116,32,111,102,32,109,121,32,86,65,84,44,84,32,97,103,114,101,101,32,116,111,32,116,104,101,32,116,101,114,109,115,32,97,110,100,32,99,111,110,100,105,116,105,111,110,115,32,111,102,32,84,97,120,32,70,114,101,101,32,69,97,115,121,32,40,112,108,101,97,115,101,32,115,101,101,32,104,116,116,112,58,47,47,119,119,119,46,116,97,120,45,102,114,101,101,45,101,97,115,121,46,99,111,109,47,116,101,114,109,115,45,97,110,100,45,99,111,110,100,105,116,105,111,110,115,47,41,46,-51,-88,-71,-3,-57,-87,-54,-16,-78,-94,-52,-31,-67,-69,-76,-53,-79,-19,-72,-15,-64,-76,-51,-53,-53,-80,-93,-84,-50,-46,-51,-84,-46,-30,84,97,120,32,70,114,101,101,32,69,97,115,121,-46,-41,-56,-85,-51,-53,-75,-60,-52,-11,-65,-18,-70,-51,-52,-11,-68,-2,-95,-93,-93,-88,-72,-9,-49,-18,-52,-11,-65,-14,-49,-22,-57,-23,-57,-21,-78,-50,-65,-68,-93,-70,104,116,116,112,58,47,47,119,119,119,46,116,97,120,45,102,114,101,101,45,101,97,115,121,46,99,111,109,47,116,101,114,109,115,45,97,110,100,45,99,111,110,100,105,116,105,111,110,115,47,-93,-87,27,69,0,10,10,27,97,0,27,69,15,67,85,83,84,79,77,69,82,32,83,73,71,78,65,84,85,82,69,32,-60,-6,-75,-60,-57,-87,-41,-42,27,69,0,10,10,27,97,0,27,69,15,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,27,69,0,10,27,97,0,27,69,15,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,27,69,0,10,27,97,0,27,69,15,48,53,47,67,85,83,84,79,77,32,86,69,82,73,70,73,67,65,84,73,79,78,32,65,78,68,32,83,84,65,77,80,10,-70,-93,-71,-40,-47,-23,-42,-92,-72,-57,-43,-62,27,69,0,10,27,97,0,27,69,15,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,27,69,0,10,27,97,0,27,69,15,84,104,101,32,112,117,114,99,104,97,115,101,100,32,103,111,111,100,115,32,104,97,118,101,32,98,101,101,110,32,112,114,101,115,101,110,116,101,100,32,102,111,114,32,101,120,112,111,114,116,46,67,117,115,116,111,109,115,32,111,114,32,111,116,104,101,114,32,97,117,116,104,111,114,105,122,101,100,32,118,101,114,105,102,105,99,97,116,105,111,110,32,111,102,32,101,120,112,111,114,116,32,102,114,111,109,32,116,104,101,32,69,85,46,84,104,101,32,105,110,102,111,114,109,97,116,105,111,110,32,111,102,32,116,104,101,32,101,120,112,111,114,116,101,114,32,105,115,32,105,100,101,110,116,105,99,97,108,32,116,111,32,116,104,111,115,101,32,111,110,32,112,97,115,115,112,111,114,116,32,111,114,32,111,116,104,101,114,32,116,114,97,118,101,108,32,100,111,99,117,109,101,110,116,115,46,27,69,0,10,10,10,10,10,10,27,97,0,27,69,15,68,97,116,101,32,97,110,100,32,115,105,103,110,97,116,117,114,101,58,27,69,0,10,10,27,97,0,27,69,15,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,-95,-86,27,69,0,10,10,27,97,0,27,69,15,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,27,69,0,10,27,97,0,27,69,15,48,54,47,82,69,70,85,78,68,32,77,69,84,72,79,68,32,-51,-53,-53,-80,-73,-67,-54,-67,27,69,0,10,27,97,0,27,69,15,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,42,27,69,0,10,27,97,0,27,69,15,65,76,73,80,65,89,32,77,79,66,73,76,69,32,78,79,46,27,69,0,10,27,97,0,27,69,15,-42,-89,-72,-74,-79,-90,-43,-53,-70,-59,-93,-88,-54,-42,-69,-6,-70,-59,-62,-21,-93,-87,27,69,0,10,10,27,97,0,27,69,15,79,110,108,121,32,100,111,97,98,108,101,32,111,110,116,111,32,116,104,101,32,65,108,105,112,97,121,32,97,99,99,111,117,110,116,46,78,111,32,99,97,115,104,32,114,101,102,117,110,100,44,97,108,115,111,32,110,111,32,114,101,102,117,110,100,32,111,110,116,111,32,116,104,101,32,99,114,101,100,105,116,32,99,97,114,100,46,27,69,0,10,27,97,0,27,69,15,-56,-85,-74,-18,-53,-80,-67,-16,-67,-85,-51,-88,-71,-3,-42,-89,-72,-74,-79,-90,-73,-75,-69,-40,-75,-67,-60,-6,-75,-60,-42,-89,-72,-74,-79,-90,-43,-53,-70,-59,27,69,0,10,27,97,0,27,69,15,78,111,32,114,101,102,117,110,100,32,119,105,116,104,111,117,116,32,111,114,105,103,105,110,97,108,32,112,117,114,99,104,97,115,101,32,114,101,99,101,105,112,116,32,97,110,100,32,99,117,115,116,111,109,32,115,116,97,109,112,46,27,69,0,10,27,97,0,27,69,15,-61,-69,-45,-48,-45,-48,-48,-89,-71,-70,-50,-17,-75,-91,-66,-35,-68,-80,-70,-93,-71,-40,-47,-23,-42,-92,-72,-57,-43,-62,-93,-84,-53,-80,-65,-18,-67,-85,-50,-34,-73,-88,-51,-53,-69,-71,-95,-93,27,69,0,10,10,27,97,0,27,69,15,-46,-41,-56,-85,-51,-53,32,-80,-39,-73,-42,-80,-39,-56,-85,-74,-18,-51,-53,-53,-80,27,69,0,10,27,97,1,27,69,15,-75,-62,-71,-6,-41,-55,-47,-81,-75,-25,-69,-80,43,52,57,32,48,54,49,57,54,47,55,55,52,53,49,45,55,49,27,69,0,10,27,97,1,27,69,15,-42,-48,-71,-6,-65,-51,-73,-2,-56,-56,-49,-33,52,48,48,32,56,50,55,56,32,56,54,54,27,69,0,10,27,97,1,27,69,15,119,119,119,46,116,97,120,45,102,114,101,101,45,101,97,115,121,46,99,111,109,27,69,0,10,27,97,1,27,69,15,115,117,112,112,111,114,116,64,116,97,120,45,102,114,101,101,45,101,97,115,121,46,99,111,109,27,69,0,10,10,10,29,86,66,0";

    @Test
    public void testByte() {
//        String[] tempTest = testStr.split(",");
//        Byte[] bytes = new Byte[tempTest.length];
//        for (int i = 0; i < bytes.length; i++) {
//            bytes[i] = Byte.parseByte(tempTest[i]);
//        }
//        Flowable.just(testStr).map(s -> {
//            String[] tempTest = testStr.split(",");
//            Byte[] bytes = new Byte[tempTest.length];
//            for (int i = 0; i < bytes.length; i++) {
//                bytes[i] = Byte.parseByte(tempTest[i]);
//            }
//            return bytes;
//        }).subscribe(aByte -> {
//            // LogUtils.i(aByte);
//
//        });

    }

//    @Test
//    public void testAlias() {
//        System.out.println(isValidTagAndAlias("elysJh1da@W1Q2i3OiI0NyJ9"));
//    }
//
//    // 校验Tag Alias 只能是数字,英文字母和中文
//    private static boolean isValidTagAndAlias(String s) {
//        Pattern p = Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_!@#$&*+=.|]+$");
//        Matcher m = p.matcher(s);
//        return m.matches();
//    }

}