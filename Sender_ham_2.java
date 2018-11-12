import java.util.*;

class Sender_ham_2
{

	public static void main(String[] args) 
	{
		Scanner sc =new Scanner(System.in);
		HammEncode he = new HammEncode();
		System.out.println("Start Hamming sender code...");
		System.out.println("Enter nmber of bits");
		int len_data = sc.nextInt();
		int dataWord [] = new int [len_data];
		for(int i=0;i<len_data;i++)
		{
			System.out.println("Enter a bit");
			dataWord[i]=sc.nextInt();
		}
		System.out.print("Entered dataWord is ::");
		he.disp(dataWord);
		System.out.println();
		// Calculate r
		int r=0;
		for (int i=0;i<len_data ;i++ ) 
		{
				if(he.powOf2(i)>=(len_data+1+i))
				{
					r=i;
					System.out.println("r :: "+r);
					break;
				}	
		}

		// calculate parity p1,p2,p4,p8.... in the pattern of two's power pattern...
		// numbers in parity is like for eg. in p1{1,3,5,7,9},p2{2,3,6,7}..
		// here the logic to get the parity number is like this if the number starts with one then enter 1 in the array and do not enter 2 enter 3
		// in general enter if number starts with n enter number starting from n till n nd then skip n number then again enter next n

		int codeWord [] = new int [len_data+r];
		for(int k=0,j=0,i=0;i<codeWord.length;i++)
		{
			if(he.powOf2(k)==(i+1))
			{
				codeWord[i]=-1;
				k++;
			}
			else
			{	try{

				if(j<len_data)
				codeWord[i]=dataWord[j];
				j++;
				}catch(Exception e){System.out.println(e);}
				
			}
		}
		System.out.println("Testing codeword ::");
		he.disp(codeWord);
		System.out.println();
		//
			
		he.encode(codeWord);
		System.out.println("Encoded codeWord is :: ");
		he.disp(codeWord);


	}

}

class HammEncode
{
	int powOf2(int r)
	{
		int power=1;
		for(int i=0;i<r;i++)
			power=power*2;
		return power;
	}

	void disp(int arr[])
	{
		int arrlen=arr.length;
		for (int i=0;i<arrlen ;i++ ) {
			System.out.print(arr[i]);
			
		}
	}

	void encode(int codeWord[])
	{

		int par,parity;
		int count1,count0,set;
		count1=count0=0;
		for(int i=0;i<codeWord.length;i++)
		{
			if(codeWord[i]==-1)
			{
				par=i+1;
				System.out.println("-1 found at "+par+"\n\n");
				set=1;
				for (int j=i; j<codeWord.length; ) 
				{
					if(set==1)
					{
						for(int k=0;k<par&&j<codeWord.length;k++,j++)
						{	

							try
							{
								
								System.out.println("codeWord["+(j+1)+"] :::: "+codeWord[j]);
								if(codeWord[j]!=-1)
								{
									if(codeWord[j]==0)
									{
									count0++;
									}	
									else
									{
									if(codeWord[j]==1)
										count1++;
									}
								}
								else
								{
									System.out.println("Did not calculate**********");
								}
							}catch(Exception e){System.out.println(e+"at line 77");}
						}
						set=0;
					}
					else
					{
						for (int k=0; k<par;k++ ) 
						{
							System.out.println("parity  :::: "+par);
							j++;
								
						}
						set=1;
					}
				}
				//even parity
				if(count1%2==0)
				{
					codeWord[i]=0;
					count1=0;
					count0=0;
				}
				else
					{codeWord[i]=1;
						count0=0;
						count1=0;
					}
			}
		}
	}
}